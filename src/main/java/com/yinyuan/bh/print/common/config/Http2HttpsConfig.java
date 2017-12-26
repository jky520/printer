package com.yinyuan.bh.print.common.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http或https请求配置
 * Created by DT人 on 2017/12/20 19:58.
 */
/*@Configuration
public class Http2HttpsConfig {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory(){//1
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("*//*");
                collection.addMethod("GET");
                collection.addMethod("POST");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }
}*/
