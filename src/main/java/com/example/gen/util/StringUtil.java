package com.example.gen.util;


import com.example.gen.config.SetupConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zx on 14/10/24.
 */
public class StringUtil {

    public static String capFirst(String str) {
        String firstC = str.substring(0, 1);

        return str.replaceFirst(firstC, firstC.toUpperCase());
    }


    public static String javaStyle(String columnName) {
        String patternStr = "(_[a-z,A-Z])";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(columnName.toLowerCase());
        StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            String replaceStr = matcher.group();
            matcher.appendReplacement(buf, replaceStr.toUpperCase());
        }
        matcher.appendTail(buf);
        return buf.toString().replaceAll("_", "");
    }

    //去前缀
    public static String javaStyleOfTableName(String tableName) {
        String prefixs = SetupConfig.getInstance().getIgnorePrefix();
        String[] ps = prefixs.split(",");
        for (int i = 0; i < ps.length; i++) {
            if (tableName.startsWith(ps[i])) {
                tableName = tableName.replaceAll(ps[i], "");
            }
        }
        return StringUtil.javaStyle(tableName);
    }


    // 类名
    public static String className(String tableName) {
        return capFirst(javaStyleOfTableName(tableName));
    }

    // 首字母小写
    public static String lowerInit(String s) {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String excuteName(String projectName) {
        String[] patternStr = projectName.split("-");
        StringBuffer buf = new StringBuffer();
        for (String text:patternStr){
            buf.append(upperCase(text));
        }
        return  buf.toString();
    }
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
    public static void main(String[] args)throws Exception {
        System.out.println(excuteName("gw-cloud-mydemo"));
    }
}
