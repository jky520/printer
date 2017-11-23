package com.yinyuan.bh.print.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

/**
 * 打印工具类
 * Created by DT人 on 2017/11/23 10:14.
 */
public class PrintUtil {
    public static void print(String fileName) {
        String filePath = WordUtil.WORD_BASE_URL + File.separator + fileName;
        System.out.println("进入打印模块");
        ComThread.InitSTA();
        ActiveXComponent word = new ActiveXComponent("Word.Application");
        Dispatch doc = null;
        try {

            Dispatch.put(word, "Visible", new Variant(false));
            Dispatch docs = word.getProperty("Documents").toDispatch();
            doc = Dispatch.call(docs, "Open", filePath).toDispatch();

            // 开始打印
            Dispatch.call(doc, "PrintOut"); // 打印


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("打印失败");
        } finally {
            try {
                if (doc != null)
                    Dispatch.call(doc, "Close", new Variant(0));
                // word.invoke("Quit", new Variant[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 始终释放资源
            ComThread.Release();
        }
    }

    public static void main(String[] args) {
        //print();
        System.out.println(System.getProperty("java.library.path"));
    }
}
