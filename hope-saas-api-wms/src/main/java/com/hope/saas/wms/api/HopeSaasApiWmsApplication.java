package com.hope.saas.wms.api;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.hope.saas.wms.api.config.annotation.SubmitValidateInterceptor;
import com.hope.saas.wms.api.config.interceptor.InterceptorUtil;
import com.hope.saas.wms.api.config.interceptor.LoginInterceptor;
import com.hope.saas.wms.api.config.interceptor.ReqHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Yangqi.Pang
 */
@SpringBootApplication
public class HopeSaasApiWmsApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(HopeSaasApiWmsApplication.class);
        InterceptorUtil.addInterceptor(new SubmitValidateInterceptor(), "/api/**", "/wms/**");
        InterceptorUtil.addInterceptor(new ReqHandlerInterceptor(), "/api/**");
        InterceptorUtil.addInterceptor(new LoginInterceptor(), "/**/**.htm");
        springApplication.run(args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        List<HandlerInterceptor> list = InterceptorUtil.get();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                registry.addInterceptor(list.get(i))
                        .addPathPatterns(InterceptorUtil.getpathPatterns(i));
            }
        }
        list.clear();
        super.addInterceptors(registry);
    }
}
