package com.yinyuan.bh.print.common.timer;

import com.yinyuan.bh.print.common.utils.WordUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定时删除多余的文档
 * 通过正则表达式的方式匹配文件名
 *
 * Created by DT人 on 2017/11/23 11:41.
 */
@Component
public class MyTimer {

    // 每天02点00分00秒时执行
    @Scheduled(cron = "00 00 02 * * ?")
    public void deleteWordTimeCorn() {
        File f = new File(WordUtil.WORD_BASE_URL);
        String reg = "([0-9a-z]{32}.docx)";
        Pattern pattern = Pattern.compile(reg);
        Arrays.stream(f.listFiles()).peek(x -> System.out.println("文件名：" + x.getName())).forEach(x -> {
            Matcher m = pattern.matcher(x.getName());
            if(m.find()) { // 匹配用m.match()
                //System.out.println("文件名："+ m.group(1)); // 正则的()表示分组匹配并获取，组数根据括号个数来的，组数从1开始
                WordUtil.deleteFile(m.group(1));
            }
        });
    }

    public static void main(String[] args) {

    }
}
