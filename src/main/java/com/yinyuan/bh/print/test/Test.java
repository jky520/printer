package com.yinyuan.bh.print.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created by DT人 on 2017/11/29 16:43.
 */
public class Test {
    @Autowired
    private static RestTemplate restTemplate;

    /*public static void main(String[] args) {
        String url = "https://sso.buaa.edu.cn/logout";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        System.out.println(json);
    }*/
}
