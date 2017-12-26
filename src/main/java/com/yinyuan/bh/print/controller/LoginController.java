package com.yinyuan.bh.print.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录与登出的控制器
 * Created by DT人 on 2017/11/29 12:12.
 */
@RestController
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

   /* @GetMapping("/loginout")
    public String loginout() {
        String url = "https://sso.buaa.edu.cn/logout";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        return json.toString();
    }*/

    /*@GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println("login=======>");
        System.out.println(session.getAttribute("edu.yale.its.tp.cas.client.filter.user"));
        System.out.println("------>"+"j_security_check?j_username="+session.getAttribute("edu.yale.its.tp.cas.client.filter.user")+"&j_password=");
        if(session.getAttribute("edu.yale.its.tp.cas.client.filter.user") != null) {
            ServletRequest req=(ServletRequest) request;
            ServletResponse res=(ServletResponse) response;
            String nextURL = "j_security_check?j_username="+session.getAttribute("edu.yale.its.tp.cas.client.filter.user")+"&j_password=";
            response.sendRedirect(nextURL);
        }
    }*/


}
