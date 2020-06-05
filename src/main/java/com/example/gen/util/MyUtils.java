package com.example.gen.util;



import com.example.gen.config.Group;
import com.example.gen.config.SetupConfig;
import com.example.gen.config.TemplateMapping;

import java.io.File;

/**
 * Created by zx on 14/10/24.
 */
public class MyUtils {

    private static SetupConfig config = SetupConfig.getInstance();

    public static String getTemplatePath(TemplateMapping m) {
        return  m.getTemplate();
    }

    public static String getGroupName(String tableName) {
        Group[] groups = config.getGroups();
        String name = null;
        for (Group g : groups) {
            name = g.findGroupName(tableName);
            if (name != null) {
                return name;
            }
        }
        return null;
    }

    public static String getModelName(String tableName, String separator) {
        String g = getGroupName(tableName);
        if (g == null) {
            return StringUtil.javaStyleOfTableName(tableName);
        }
        System.out.println("组名不为空："+g);
        return g ;
    }

    public static String getOutPutPath(TemplateMapping m, String tableName) {
        String path = SetupConfig.USER_DIR + SetupConfig.SEPARATOR
                + config.getProject() + SetupConfig.SEPARATOR
                + "out" + SetupConfig.SEPARATOR
                //原
                //+m.buildDir("", config.getPackagePath(), getModelName(tableName, "/")) + SetupConfig.SEPARATOR
                //加内层目录后
                +m.buildDir("", config.getPackagePath(), getModelName(tableName, "/"),config.getInnerPackagePath()) + SetupConfig.SEPARATOR
                ;
        path += m.getsPadding() + StringUtil.className(tableName) + m.getePadding() + "." + m.getSuffix();
        System.out.println("#####"+getModelName(tableName, "/"));
        System.out.println("getOUtPutPath:"+path);
        mkdir(path);
        return path;
    }

    public static void mkdir(String filePath) {
        int index = filePath.lastIndexOf("\\");
        int index2 = filePath.lastIndexOf("/");
        if (index + index2 == -2) return;
        index = index > index2 ? index : index2;

//        System.out.println("######11#####"+filePath);
//        System.out.println("######filePath.substring(0, index)#####"+filePath.substring(0, index));
//        System.out.println("######exists#####"+new File(filePath.substring(0, index)).exists());

        if (index != -1 && !new File(filePath.substring(0, index)).exists()) {
            System.out.println("mkdir - " + filePath.substring(0, index));
            new File(filePath.substring(0, index)).mkdirs();
        }
    }

    public static String buildModelPackage(String tableName) {
        return config.getPackagePath() + "." + getModelName(tableName, ".");
    }

}
