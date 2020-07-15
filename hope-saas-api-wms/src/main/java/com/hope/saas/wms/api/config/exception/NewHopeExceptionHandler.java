package com.hope.saas.wms.api.config.exception;

import com.alibaba.fastjson.JSON;
import com.hope.saas.api.util.exception.NewHopeException;
import com.hope.saas.api.util.exception.ParamsFormatException;
import com.hope.saas.api.util.exception.ResubmitException;
import com.hope.saas.api.util.exception.SecurityCheckException;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.enums.ExceptionEnum;
import com.hope.saas.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/3/20 16:27
 */
@ControllerAdvice
@Slf4j
public class NewHopeExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(Exception e, HttpServletResponse response, HttpServletRequest request) {
        Result result;
        log.error("异常信息:", e);
        if (e instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException httpMessageNotReadableException = (HttpMessageNotReadableException) e;
            return Result.error("参数类型错误！" + httpMessageNotReadableException.getLocalizedMessage().split(":")[0]);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.error(ResultCodeEnum.METHOD_NOT_ALLOWED, e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exc = (MethodArgumentNotValidException) e;

            BindingResult bindingResult = exc.getBindingResult();

            List<String> errorList = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();

                for (ObjectError error : allErrors) {
                    errorList.add(error.getDefaultMessage());
                }
            }
            result = Result.validationError(ExceptionEnum.PARAMS_VALIED_ERROR.getCode(), errorList);
        } else if (e instanceof ResubmitException) {
            result = Result.resubmitError(e.getMessage());
        } else if (e instanceof ParamsFormatException) {
            result = Result.paramsFormatException();
        } else if (e instanceof SecurityCheckException) {
            result = Result.safetyCheck(e.getMessage());
        } else if (e instanceof NewHopeException) {
            result = Result.error(e.getMessage());
        } else {
            result = Result.error(ResultCodeEnum.API_ERROR, e.getMessage());
        }
        if (isAjax(request)) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setAttributesMap(JSON.parseObject(JSON.toJSONString(result), Map.class));
            ModelAndView mav = new ModelAndView();
            mav.setViewName("500");
            mav.setView(view);
            mav.addObject("exMsg", getStackTrace(e));
            return mav;
        } else {
            return result;
        }

    }


    /**
     * 判断是不是ajax请求
     *
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
