package com.yinyuan.bh.print.common.utils;

import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * 权限信息工具类
 * Created by DT人 on 2017/12/18 15:52.
 */
public class AuthUtil {

    /**
     * 获取权限的基本属性
     * @return
     */
    private static AttributePrincipal getPrincipalAttributes(HttpServletRequest request) {
        AttributePrincipal principal  =  (AttributePrincipal)request.getUserPrincipal();
        return principal;
    }

    /**
     * 获得所有的属性信息
     * @return
     */
    public static Map getAttris(HttpServletRequest request) {
        AttributePrincipal principal =  getPrincipalAttributes(request);
        Map attributes = principal.getAttributes();
        return attributes;
    }

    /**
     * 通过属性名获得属性值
     * @param arrName
     * @return
     */
    public static String getAttrByAttrName(HttpServletRequest request, String arrName) {
        AttributePrincipal principal =  getPrincipalAttributes(request);
        //如果需要获取单独的属性使用以下方式，如教工编号：
        Map attributes = principal.getAttributes();
        String employeeNumber = (String)attributes.get(arrName);
        return employeeNumber;
    }
}
