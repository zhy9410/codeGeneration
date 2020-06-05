package com.example.gen.util;


import java.io.*;

/**
 *
 * 文件类数据的操作
 * */
public class FileUtils {

    /**
     *
     * 读取文件
     * */
    public static String executeString(String path) {


        if (path == null || "".equals(path.trim())) {
            System.err.println("path is not exist");
            System.exit(0);
        }

        StringBuffer list = new StringBuffer();
        BufferedReader intxt= null;

        try {

            intxt = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), "UTF-8"));

            String line = null;
            while ((line = intxt.readLine()) != null) {
                list.append(line).append("\n\r");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("fail in reading parameter file" + path);
        } finally {
            try {
                intxt.close();
            } catch (Exception e) {
            }
        }

        return list.toString();
    }

    /**
     * 写文件
     *
     * @param path
     * @param content
     * @return
     */
    public static boolean writeFile(String path, String content) {
        if (content == null) {
            content = "";
        }
        BufferedWriter output = null;
        try {
            File file = new File(path);
            // 文件不存在则创建
            if ((file.exists() == false) && (file.createNewFile() == false)) {
                // 文件创建失败
                return false;
            }

            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")); ;
            output.write(content);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                output.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 复制 文件
     *
     * @param sourceFile
     * @param destFile
     * @throws IOException
     */
    public static  void copyAllFiles(String sourceFile, String destFile) throws IOException {
        File souFile=new File(sourceFile);
        File desFile=new File(destFile);
        if(!souFile.isDirectory()){
            return;
        }
        org.apache.commons.io.FileUtils.copyDirectory(souFile,desFile);
    }

    /**
     * 删除文件 或者 目录
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (path == null || "".equals(path.trim())) {
            return false;
        }
        File file = new File(path);

        // 文件存在
        if (file.exists() == true) {
            if (file.isDirectory()) {
                try {
//					FileUtils.cleanDirectory(dir);//清空目录下的文件
//			        FileUtils.deleteDirectory(dir);//删除目录和目录下的文件
                    org.apache.commons.io.FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    return false;
                }
            } else {
                file.delete();
            }
        }
        return true;
    }
}
