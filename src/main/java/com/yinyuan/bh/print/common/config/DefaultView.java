package com.yinyuan.bh.print.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 默认首页的设置
 * Created by DT人 on 2017/12/15 11:24.
 */
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/" ).setViewName("forward:/school.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers(registry);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
