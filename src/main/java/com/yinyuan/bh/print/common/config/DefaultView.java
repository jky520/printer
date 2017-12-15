package com.yinyuan.bh.print.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
        System.out.println("进来了");
        registry.addViewController("/" ).setViewName("forward:/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
        System.out.println("进来了============》");
        super.addViewControllers(registry);
    }
}
