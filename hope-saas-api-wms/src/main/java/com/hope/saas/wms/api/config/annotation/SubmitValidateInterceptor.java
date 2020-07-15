package com.hope.saas.wms.api.config.annotation;

import com.alibaba.fastjson.JSON;
import com.hope.saas.api.util.exception.ResubmitException;
import com.hope.saas.common.constants.RedisKeyConstant;
import com.hope.saas.common.constants.TokenConstants;
import com.hope.saas.common.enums.ResultCodeEnum;
import com.hope.saas.wms.api.config.redis.RedisTemplateUtils;
import com.hope.saas.wms.api.config.start.StartConstant;
import com.hope.saas.wms.api.config.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Maduo
 * @date 2020/3/26 16:21
 */
@Slf4j
public class SubmitValidateInterceptor extends HandlerInterceptorAdapter {

    private RedisTemplateUtils redisTemplateUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (redisTemplateUtils == null) {
            this.redisTemplateUtils = SpringUtil.getBean(RedisTemplateUtils.class);
        }
        //只在开发和生产环境开启重复提交校验
        if (!StartConstant.active.equals("test") && handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            SubmitValidate annotation = method.getAnnotation(SubmitValidate.class);

            if (annotation != null) {
                log.info("===> 重复提交拦截校验开始");

                String key = RedisKeyConstant.WMS_API_REDIS_KEY + request.getRequestURI().replaceAll("/", ".") + ":"
                        + request.getHeader(TokenConstants.TOKEN);
                //需要做重复校验
                boolean b = redisTemplateUtils.exists(key);

                log.info("===> 重复校验redis是否存在数据：{}", b);
                if (b) {
                    throw new ResubmitException(ResultCodeEnum.RESUBMIT_ERROR.getStatus(), annotation.times() + "s内" + ResultCodeEnum.RESUBMIT_ERROR.getDesc());
                } else {
                    //请求数据保存5s
                    redisTemplateUtils.set(key, JSON.toJSONString(request.getParameterMap()), annotation.times());
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return super.preHandle(request, response, handler);
        }
    }

}
