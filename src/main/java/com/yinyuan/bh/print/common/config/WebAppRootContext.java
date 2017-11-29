package com.yinyuan.bh.print.common.config;

import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果是war包项目，我们可以将Servlet、Filter、Listener注册到WebApplicationInitializer的实现类中
 * 无web.xml文件时的上下文监听的配置
 * Created by DT人 on 2017/11/23 16:25.
 */
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class WebAppRootContext implements ServletContextInitializer {
    @Value("${appUri}")
    private String appUri; // 本应用所部署的服务器地址
    @Value("${server.port}")
    private String serverPort; // 本应用所启动的端口

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic casFilter = servletContext.addFilter("CAS Authentication Filter","org.jasig.cas.client.authentication.AuthenticationFilter");
        Map<String, String> casInitParams = new HashMap<String, String>();
        casInitParams.put("casServerLoginUrl","https://sso.buaa.edu.cn/login");
        // client:port就是需要cas需要拦截的地址和端口,一般就是这个tomcat所启动的ip和port
        casInitParams.put("serverName",appUri + ":" + serverPort);
        casFilter.setInitParameters(casInitParams);
        casFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");


        FilterRegistration.Dynamic casValidateFilter = servletContext.addFilter("CAS Validation Filter","org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter");
        Map<String, String> casValidateInitParams = new HashMap<String, String>();
        casValidateInitParams.put("casServerUrlPrefix","https://sso.buaa.edu.cn");
        casValidateInitParams.put("useSession","true");
        casValidateInitParams.put("serverName",appUri + ":" + serverPort);
        casValidateInitParams.put("redirectAfterValidation","true");
        casValidateInitParams.put("tolerance","50000");
        casValidateFilter.setInitParameters(casValidateInitParams);
        casValidateFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");


        HttpServletRequestWrapperFilter httpServletRequestWrapperFilter = new HttpServletRequestWrapperFilter();
        FilterRegistration.Dynamic casHttpFilter = servletContext.addFilter("CAS HttpServletRequest Wrapper" +
                "Filter",httpServletRequestWrapperFilter);
        casHttpFilter.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
    }
}
