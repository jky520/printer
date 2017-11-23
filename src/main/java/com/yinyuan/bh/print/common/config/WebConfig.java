package com.yinyuan.bh.print.common.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 如果是jar包部署方式，则可以将其注册到任意一个@Configuration配置类中
 * 配置类似于web.xml的方式
 * Created by DT人 on 2017/11/23 17:34.
 */
@Configuration
public class WebConfig {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public FilterRegistrationBean authenticationFilterBean() {
        FilterRegistrationBean casFilterBean = new FilterRegistrationBean();
        casFilterBean.setName("CAS Authentication Filter");
        casFilterBean.setFilter(new AuthenticationFilter());
        casFilterBean.setOrder(1);
        Map<String, String> casInitParams = new HashMap<String, String>();
        casInitParams.put("casServerLoginUrl","https://sso.buaa.edu.cn/login");
        // client:port就是需要cas需要拦截的地址和端口,一般就是这个tomcat所启动的ip和port
        casInitParams.put("serverName","http://127.0.0.1:"+serverPort);
        casFilterBean.setInitParameters(casInitParams);
        Set<String> set = new HashSet<String>();
        set.add("/*");
        casFilterBean.setUrlPatterns(set);
        return casFilterBean;
    }

    @Bean
    public FilterRegistrationBean casValidateFilterBean() {
        FilterRegistrationBean casValidateFilterBean = new FilterRegistrationBean();
        casValidateFilterBean.setName("CAS Validation Filter");
        casValidateFilterBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        casValidateFilterBean.setOrder(2);
        Map<String, String> casValidateInitParams = new HashMap<String, String>();
        casValidateInitParams.put("casServerUrlPrefix","https://sso.buaa.edu.cn");
        casValidateInitParams.put("useSession","true");
        casValidateInitParams.put("serverName","http://127.0.0.1:"+serverPort);
        casValidateInitParams.put("redirectAfterValidation","true");
        casValidateInitParams.put("tolerance","50000");
        casValidateFilterBean.setInitParameters(casValidateInitParams);
        Set<String> set = new HashSet<String>();
        set.add("/*");
        casValidateFilterBean.setUrlPatterns(set);
        return casValidateFilterBean;
    }

    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {
        FilterRegistrationBean casHttpFilter = new FilterRegistrationBean();
        casHttpFilter.setName("CAS HttpServletRequest Wrapper");
        HttpServletRequestWrapperFilter hsrwf = new HttpServletRequestWrapperFilter();
        casHttpFilter.setFilter(hsrwf);
        casHttpFilter.setOrder(3);
        Set<String> set = new HashSet<String>();
        set.add("/*");
        casHttpFilter.setUrlPatterns(set);
        return casHttpFilter;
    }
}
