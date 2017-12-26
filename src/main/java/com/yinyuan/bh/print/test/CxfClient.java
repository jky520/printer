package com.yinyuan.bh.print.test;

import com.alibaba.fastjson.JSON;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.SystemUtils.USER_NAME;

/**
 * cxf客户端测试类
 * Created by DT人 on 2017/12/18 12:59.
 */
public class CxfClient {
    public static void main(String[] args) {
        cl2();
        /*JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://192.168.224.182:8088/sapPushExpenseWebservice?wsdl");
        try {
            List<String> list = new ArrayList<>();
            list.add("测试");

            Object[] objects = client.invoke("pushExpense", list);

            System.out.println(objects[0]);

        }catch (Exception e){
            e.printStackTrace();
        }*/
    }


    /*public static void cl1() {
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL("http://192.168.200.39:8080/creazy"));  //设置要调用的接口地址以上一篇的为例子
        call.setOperationName(new QName("getUsers"));  //设置要调用的接口方法
        call.addParameter("id", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//设置参数名 id  第二个参数表示String类型,第三个参数表示入参
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//返回参数类型
        //开始调用方法,假设我传入的参数id的内容是1001   调用之后会根据id返回users信息，以xml格式的字符串返回，也可以json格式主要看对方用什么方式返回
        String result = (String) call.invoke(new Object[]{"1001"});
        System.out.println(result);//打印字符串
        Document doc = DocumentHelper.parseText(result);//转成Document对象
        Element root = doc.getRootElement();//用dom4j方式拿到xml的根节点然后打印结果信息
        System.out.println("id="+root.element("UsersID").getText()+"    name="+root.element("UsersName").getText()+"     sex="+root.element("UsersSex").getText());
    }*/

    /**
     * 动态调用方式
     */
    public static void cl2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://10.254.20.145/services/bhdxService?wsdl");
        // 需要密码的情况需要加上用户名和密码
//         client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getAbroad","123456789");
            System.out.println("返回数据:" + JSON.toJSONString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态调用方式
     */
    public static void cl3() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl");
        // 需要密码的情况需要加上用户名和密码
//         client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("qqCheckOnline","1132882670");
            System.out.println("返回数据:" + JSON.toJSONString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
