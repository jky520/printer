package com.yinyuan.bh.print.common.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 访问cxf服务端的工具类
 * Created by DT人 on 2017/12/18 15:37.
 */
public class CxfUtil {

    /**
     * 动态调用方式
     * @param wsdlUrl
     * @param method
     * @param objs
     * @return
     */
    public static Object getServiceData(String wsdlUrl, String method, Object... objs) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(method, objs);
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return objects;
    }
}