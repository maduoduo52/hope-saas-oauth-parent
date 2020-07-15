package com.hope.saas.wms.api.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.api.util.exception.SecurityCheckException;
import com.hope.saas.api.util.util.JwtUtil;
import com.hope.saas.common.constants.Constant;
import com.hope.saas.common.constants.HeaderDto;
import com.hope.saas.common.constants.RedisKeyConstant;
import com.hope.saas.common.constants.TokenConstants;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.config.redis.RedisTemplateUtils;
import com.hope.saas.wms.api.config.util.SpringUtil;
import com.hope.saas.wms.api.service.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/3/24 17:16
 */
@Slf4j
@Component
public class ReqHandlerInterceptor implements HandlerInterceptor {

    private CustomerInfoService customerInfoService;

    private RedisTemplateUtils redisTemplateUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("====>{}--{}<===", httpServletRequest.getRequestURL(), httpServletRequest.getMethod());

        long start = System.currentTimeMillis();

        if (o instanceof HandlerMethod) {
            // 忽略安全校验的请求直接放过
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Method method = handlerMethod.getMethod();
            IgnoreLogin ignoreLogin = method.getAnnotation(IgnoreLogin.class);
            if (ignoreLogin != null) {
                return true;
            }
        }

        if (customerInfoService == null) {
            this.customerInfoService = SpringUtil.getBean(CustomerInfoService.class);
        }

        if (redisTemplateUtils == null) {
            this.redisTemplateUtils = SpringUtil.getBean(RedisTemplateUtils.class);
        }

        String token = httpServletRequest.getHeader(TokenConstants.TOKEN);

        if (StringUtils.isBlank(token)) {
            throw new SecurityCheckException("请求凭证不能为空");
        }

        Map<String, Claim> tokenMap = JwtUtil.verifyToken(token);

        // 校验通过
        String tenantId = null;
        String shipperCode = null;
        if (tokenMap.containsKey(CustomerInfoTable.TENANT_ID)) {
            tenantId = tokenMap.get(CustomerInfoTable.TENANT_ID).asString();
        }
        if (tokenMap.containsKey(CustomerInfoTable.SHIPPER_CODE)) {
            shipperCode = tokenMap.get(CustomerInfoTable.SHIPPER_CODE).asString();
        }

        log.info("===>> 请求tenantId:{}, shipperCode:{}，token:{}", tenantId, shipperCode, token);
        if (StringUtils.isBlank(tenantId) || StringUtils.isBlank(shipperCode)) {
            throw new SecurityCheckException("请求凭证格式不合法");
        }

        //从redis获取token
        String obj = (String) redisTemplateUtils.getObj(RedisKeyConstant.WMS_API_CREATE_TOKEN + tenantId + shipperCode);
        if (StringUtils.isNotBlank(obj) && !obj.equals(token)) {
            throw new SecurityCheckException("请求凭证已过期，请重新获取");
        }

        //获取redis 用户信息
        String entity = (String) redisTemplateUtils.getObj(RedisKeyConstant.WMS_API_CUSTOMER_INFO + tenantId + shipperCode);
        CustomerInfoEntity customerInfoEntity;
        if (StringUtils.isBlank(entity)) {
            // 如果redis不存在，从数据库获取
            EntityWrapper<CustomerInfoEntity> wrapper = new EntityWrapper<>();
            wrapper.eq(CustomerInfoTable.TENANT_ID, tenantId);
            wrapper.eq(CustomerInfoTable.SHIPPER_CODE, shipperCode);
            customerInfoEntity = customerInfoService.selectOne(wrapper);

        } else {
            customerInfoEntity = JSON.parseObject((entity), CustomerInfoEntity.class);
        }

        if (customerInfoEntity == null) {
            throw new SecurityCheckException("未查询到相关用户");
        }
        //用户数据存入本地线程变量
        HeaderDto headerDto = HeaderDto.initEmp(customerInfoEntity);
        Constant.set(headerDto);
        log.warn("===》》token 校验时长：{} ms", System.currentTimeMillis() - start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
