package com.yinyuan.bh.print.common.utils;

import com.yinyuan.bh.print.word.Word;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 读取证明文档的工具类
 * Created by DT人 on 2017/11/21 16:28.
 */
public class WordUtil {
    public static final String WORD_BASE_URL = Word.class.getResource("").getPath().replaceFirst("/","").replace("target/classes", "src/main/java").replaceAll("/", "\\\\").replaceAll("%20"," ");;

    public static void main(String[] args) {
        /*File f = new File(WORD_BASE_URL);
        File[] fs = f.listFiles();
        for(File file : fs) {
            System.out.println(file.getName());
        }
        System.out.println(readFile("一般收入证明.docx").getName());*/
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("${name}","张三");
        params.put("${sex}","先生");
        params.put("${card}","52262819999999999999");
        params.put("${work}","工程师");
        params.put("${salary}","12000");
        params.put("${date}","二零一七年十一月二十一日");
        params.put("${header}","姜先生");
        params.put("${duties}","办公室主任");
        params.put("${company}","贵州华信有限公司");
        params.put("${address}","西南环路口");
        params.put("postcode","556700"); // 根据word模版看看替换的内容是否添加${},不加也行
        params.put("${phone}","15288888888");
        params.put("${fax}","+0851-8536429");
//        params.put("name","张三");
//        params.put("sex","先生");
//        params.put("idCard","52262819999999999999");
//        params.put("work","工程师");
//        params.put("salary","12000");
//        params.put("date","二零一七年十一月二十一日");
//        params.put("header","姜先生");
//        params.put("duties","办公室主任");
//        params.put("company","贵州华信有限公司");
//        params.put("address","西南环路口");
//        params.put("postcost","556700");
//        params.put("phone","15288888888");
//        params.put("fax","+0851-8536429");
        //readFile("一般收入证明.docx", params);
        // 替换文档关键字
        generateNewWord("一般收入证明.docx",params);
    }

    /**
     * 生成新文档
     * @param fileName
     * @param params
     */
    public static void generateNewWord(String fileName, Map<String,Object> params) {
        XWPFDocument doc = null;
        OPCPackage pack = null;
        String fiePath = WORD_BASE_URL + File.separator + fileName;
        try {
            pack = POIXMLDocument.openPackage(fiePath);
            doc = new XWPFDocument(pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fopts = null;
        String newFileName = null;
        try {
            newFileName = UUID.randomUUID().toString().replaceAll("-","") + getFileType(fileName);
            String newFilePath = WORD_BASE_URL+File.separator+newFileName;
            fopts = new FileOutputStream(newFilePath);
            doc.write(fopts);
            pack.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fopts);
        }
        String newFileName1 = generateWord(newFileName, params);
//        PrintUtil.print(newFileName1); // 在这里调用打印工具类
//        deleteFile(newFileName1);
//        deleteFile(newFileName);
    }

    /**
     * 生成新文档并替换内容
     * @param fileName
     * @param params
     * @return
     */
    public static String generateWord(String fileName, Map<String,Object> params) {
        XWPFDocument doc = null;
        OPCPackage pack = null;
        String fiePath = WORD_BASE_URL + File.separator + fileName;
        try {
            pack = POIXMLDocument.openPackage(fiePath);
            doc = new XWPFDocument(pack);
            if (params != null && params.size() > 0) {
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, params, doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fopts = null;
        String newFileName = null;
        try {
            newFileName = UUID.randomUUID().toString().replaceAll("-","") + getFileType(fileName);
            String newFilePath = WORD_BASE_URL+File.separator+newFileName;
            fopts = new FileOutputStream(newFilePath);
            doc.write(fopts);
            pack.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fopts);
        }
        return newFileName;
    }

    /**
     * 关闭输入流
     * @param fopts
     */
    public static void closeStream(FileOutputStream fopts) {
        try {
            fopts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理段落,替换关键字
     *
     * @param paragraphList
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static void processParagraphs(List<XWPFParagraph> paragraphList, Map<String, Object> param, XWPFDocument doc)
            throws InvalidFormatException, FileNotFoundException {
        if (paragraphList != null && paragraphList.size() > 0) {
            // 遍历所有段落
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    System.out.println("text==" + text);
                    if (text != null) {
                        boolean isSetText = false;
                        for (Map.Entry<String, Object> entry : param.entrySet()) {
                            String key = entry.getKey();
                            if (text.indexOf(key) != -1) {// 在Map中有这个关键字对应的键
                                isSetText = true;
                                Object value = entry.getValue();
                                if (value instanceof String) {// 文本替换
                                    /**
                                     * System.out.println("key==" + key);
                                     * text==${date}
                                     * key==${date}
                                     * 表示可以替换
                                     */
                                    if (text.contains(key)) {
                                        text = text.replace(key, value.toString());
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取文件的后缀名
     * @param fileName
     * @return
     */
    private static String getFileType(String fileName) {
        if(fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }

    /**
     * 删除文档
     * @param fileName
     */
    public static void deleteFile(String fileName) {
        File f = new File(WORD_BASE_URL + File.separator + fileName);
        synchronized (f) { // 同步代码块
            try {
                Thread.sleep(10000);
                if(f.exists()) f.delete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
