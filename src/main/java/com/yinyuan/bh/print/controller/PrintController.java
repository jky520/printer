package com.yinyuan.bh.print.controller;

import com.yinyuan.bh.print.common.utils.AuthUtil;
import com.yinyuan.bh.print.common.utils.CxfUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 打印四个模块的控制器
 * Created by DT人 on 2017/11/29 12:11.
 */
@RestController
public class PrintController {

    @Value("${webServiceUrl}")
    private String wsdlUrl; // 服务器端路劲

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取出国旅游证明数据
     * @return
     */
    @GetMapping(value = "/getAbord")
    public Object getAbord(HttpServletRequest request) {
        System.out.println("=====================getAbord");
        String employeeNumber = AuthUtil.getAttrByAttrName(request, "employeeNumber");
        System.out.println("employeeNumber:"+employeeNumber);
        Object obj = CxfUtil.getServiceData(wsdlUrl,"getAbroad",employeeNumber);
        System.out.println("最终结果：" + obj);
        return obj;
    }
}
