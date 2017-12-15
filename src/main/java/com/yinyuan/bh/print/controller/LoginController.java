package com.yinyuan.bh.print.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
public class LoginController {

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println("login=======>");
        if(session.getAttribute("edu.yale.its.tp.cas.client.filter.user") != null) {
            ServletRequest req=(ServletRequest) request;
            ServletResponse res=(ServletResponse) response;
            String nextURL = "j_security_check?j_username="+session.getAttribute("edu.yale.its.tp.cas.client.filter.user")+"&j_password=";
            response.sendRedirect(nextURL);
        }
    }
}
