package com.example.gen;


import com.example.gen.config.ProjectModule;
import com.example.gen.config.SetupConfig;
import com.example.gen.config.TemplateMapping;
import com.example.gen.core.BuildFactory;
import com.example.gen.util.FileUtils;
import com.example.gen.util.MyUtils;
import com.example.gen.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyiming
 * @date 14/10/27
 */
public class Builder {

    /**
     * beetl factory
     */
    private static BuildFactory factory = new BuildFactory();
    /**
     * config instance
     */
    private static SetupConfig config = SetupConfig.getInstance();
    /**
     * tablesList
     */
    private static List<String> tablesList = new ArrayList<String>();

    static {
        //TODO 添加表名
            tablesList.add("ZZ_KEY");

    }

    public void db2entry() {
        // iterator all template file
        TemplateMapping[] mappings = config.getMappings();

        for (TemplateMapping m : mappings) {
            for (String tableName : tablesList) {
                //原
                //String packagePath = m.buildPackage(config.getProject(), config.getPackagePath(), MyUtils.getModelName(tableName, "."));
                //新增内层目录后
                String packagePath = m.buildPackage(config.getProject(), config.getPackagePath(), MyUtils.getModelName(tableName, "."), config.getInnerPackagePath());
                //原
                //Map<String, Object> data = factory.getParams(tableName, packagePath);
                //适配现在含内层目录的需求,随之改动模板
                Map<String, Object> data = factory.getParams(tableName, config.getPackagePath(), config.getBaseBeanPath());
                factory.build(MyUtils.getTemplatePath(m), data, MyUtils.getOutPutPath(m, tableName));
            }
        }
    }

    /**
     * 构建项目方法
     * 替换pom文件中的数据，由于pom中存在其他'${}'标识，所以不能直接替换
     * 项目中对java部分数据原有生产方式不做处理
     */
    //2、创建项目结构
    public static void createProject() {
        Map<String, Object> params = new HashMap<String, Object>();//存储需要替换的参数
        Map<String, Object> pathMap = new HashMap<String, Object>();//存储需要将文件写入的位置
        ProjectModule[] projects = config.getModules();
        String projectName = config.getProjectName();
        String pathDir = System.getProperty("user.dir") + File.separator + config.getProject() + File.separator + "out" + File.separator;//输出地址

        String str1 = System.getProperty("user.dir");
        config.getProject();
        String ftlPath = System.getProperty("user.dir") + "/" + config.getProject() + "/src/main/resources/project/";//模板存放地址
        File file = new File(pathDir);
        params.put("projectName", config.getProjectName());
        params.put("parentProject", config.getParentProject());
        params.put("packagePath", config.getPackagePath());
        params.put("depencePackage", config.getDepencePackage());

        System.out.println(config.getProjectName() + "----" + config.getParentProject() + "----" + config.getPackagePath());
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        if (projectName != null && !"".equals(projectName)) {
            pathDir = pathDir + projectName + File.separator;
            file = new File(pathDir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
            pathMap.put("parent", pathDir + "pom.xml");//当前位置下写入父项目的pom.xml文件
            ProjectModule[] modules = config.getModules();
            for (int i = 0; i < modules.length; i++) {
                ProjectModule module = modules[i];
                String dirPath = pathDir + config.getProjectName() + "-" + module.getModule() + File.separator;//创建文件路径
                file = new File(dirPath);
                if (!file.isDirectory()) {
                    file.mkdir();
                    File resourceFile = new File(file.getAbsolutePath() + File.separator + "src/main/resources");//需要创建src/main/java,src/main/resources
                    String includes = module.getIncludes();
                    if (!resourceFile.isDirectory()) {
                        resourceFile.mkdirs();

                        if (includes.contains("mappings")) {
                            resourceFile = new File(resourceFile.getAbsolutePath() + File.separator + "mybatis/mappings");
                        }
                        if (includes.contains("config")) {
                            resourceFile = new File(resourceFile.getAbsolutePath() + File.separator + "config");
                        }
                        if (!resourceFile.isDirectory()) {
                            resourceFile.mkdirs();
                        }
                    }
                    File javaPackage = new File(file.getAbsolutePath() + File.separator + "src/main/java");//需要创建src/main/java,src/main/resources
                    if (!javaPackage.isDirectory()) {
                        javaPackage.mkdirs();
                        javaPackage = new File(javaPackage.getAbsolutePath() + File.separator + config.getPackagePath());//创建package
                        javaPackage.mkdir();
                        String[] incPathes = includes.split(",");
                        for (String path : incPathes) {
                            if (includes.contains("mappings") || includes.contains("config")) {
                                continue;
                            }
                            File childFile = new File(javaPackage.getAbsolutePath() + File.separator + path);
                            if (!childFile.isDirectory()) {
                                childFile.mkdirs();
                            }
                        }
                    }
                    File testPackage = new File(file.getAbsolutePath() + File.separator + "src/test/java");//需要创建src/main/java,src/main/resources
                    if (!testPackage.isDirectory()) {
                        testPackage.mkdirs();
                    }

                }

                pathMap.put(module.getModule(), pathDir + config.getProjectName() + "-" + module.getModule() + File.separator + "pom.xml");//添加pom文件全路径
            }
        }
        //3、创建pom文件，并放到对应的项目下
        createPomFile2Project(pathMap, params, ftlPath);
        //4、创建项目配置.properties项目
        createProperties2Project(params);

        createExecuteMainMethod(params);
    }

    /**
     * 创建执行程序Applocation
     */
    private static void createExecuteMainMethod(Map<String, Object> params) {
        String moduleName = "service";
        String pathDir = System.getProperty("user.dir") + "/" + config.getProject() + File.separator + config.getResourceDirection() + "/config_java/";//配置文件路径
        String descFile = System.getProperty("user.dir") + File.separator + config.getProject() + File.separator + "out" + File.separator
                + config.getProjectName() + File.separator + config.getProjectName() + "-" + moduleName + File.separator + config.getJavaDirection() + File.separator;
        File file = new File(pathDir);
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            String excuteProject = StringUtil.excuteName(config.getProjectName());
            params.put("excuteProject", excuteProject);
            for (String fileName : fileNames) {
                String ftlSrcPath = pathDir + fileName;
                String outPut = "";
                String content = replaceContent(ftlSrcPath, params);
                if (fileName.contains("execute")) {
                    outPut = descFile + config.getPackagePath() + File.separator + excuteProject + "Application.java";
                } else {
                    File extFile = new File(descFile + config.getPackagePath() + File.separator + "config/");
                    if (!extFile.isDirectory()) {
                        extFile.mkdirs();
                    }
                    outPut = descFile + config.getPackagePath() + File.separator + "config/" + fileName.replace("ftl", "java");
                }
                if (!FileUtils.writeFile(outPut, content)) {
                    System.out.println(descFile + "--->创建失败");
                }
            }
        }
    }


    private static String replaceContent(String ftlSrcPath, Map<String, Object> params) {
        String content = FileUtils.executeString(ftlSrcPath);

        for (String val : params.keySet()) {
            String repCon = "${" + val + "}";
            String value = params.get(val).toString();
            content = content.replace(repCon, value);
        }
        return content;
    }

    /**
     * 创建项目.properties文件，由于系统业务逻辑处理在service子项目中，这里使用定制方式进行处理
     *
     * @param params 需要替换的数据
     */
    private static void createProperties2Project(Map<String, Object> params) {
        String moduleName = "service";
        String pathDir = System.getProperty("user.dir") + "/" + config.getProject() + File.separator + config.getResourceDirection() + "/config_resource/";//配置文件路径
        String descFile = System.getProperty("user.dir") + File.separator + config.getProject() + File.separator + "out" + File.separator
                + config.getProjectName() + File.separator + config.getProjectName() + "-" + moduleName + File.separator + config.getResourceDirection() + File.separator;
        File file = new File(pathDir);
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            for (String fileName : fileNames) {
                String ftlSrcPath = pathDir + fileName;
                String outPut = "";
                String content = replaceContent(ftlSrcPath, params);

                if (fileName.contains("mybatis")) {
                    File extMybatis = new File(descFile + "mybatis");
                    if (!extMybatis.isDirectory()) {
                        extMybatis.mkdir();
                    }
                    outPut = descFile + "mybatis" + File.separator + fileName.replace("ftl", "xml");
                } else {
                    outPut = descFile + fileName.replace("ftl", "properties");
                }

                if (!FileUtils.writeFile(outPut, content)) {
                    System.out.println(descFile + "--->创建失败");
                }
            }
        }
    }

    /**
     * 对应的第三步，将pom文件写到对应路径下
     */
    private static void createPomFile2Project(Map<String, Object> pathMap, Map<String, Object> params, String ftlPath) {
        for (String key : pathMap.keySet()) {//输出地址
            String ftlSrcPath = ftlPath + key + "_pom.ftl";//模板地址
            String content = replaceContent(ftlSrcPath, params);
            String outPath = pathMap.get(key).toString();

            if (!FileUtils.writeFile(outPath, content)) {
                System.out.println(outPath + "--->创建失败");
            }
        }
    }

    //4、重新将文件复制到对应目录下
    public static void moveFile2Project_new(String projectPathdir) throws Exception {
        ProjectModule[] modules = config.getModules();
        String pathdir = projectPathdir + File.separator + config.getPackagePath() + File.separator;

        for (ProjectModule module : modules) {
            String[] includes = module.getIncludes().split(",");
            boolean isService = false;//用于控制service文件的操作
            String moduleName = module.getModule();
            String commPath = projectPathdir + config.getProjectName() + File.separator + config.getProjectName() + "-" + moduleName;
            for (String inc : includes) {
                String sourceFile = pathdir + inc;
                if (inc.equals("service")) {
                    isService = true;
                }
                String destFile = commPath
                        + File.separator + config.getJavaDirection() + File.separator + config.getPackagePath() + File.separator + inc;
                if (inc.equals("mappings")) {
                    destFile = commPath
                            + File.separator + config.getResourceDirection() + File.separator + "mybatis/" + inc;
                }
                if (inc.equals("config")) {
                    destFile = commPath
                            + File.separator + config.getResourceDirection() + File.separator + inc;
                }
                if ("".equals(sourceFile) || "".equals(destFile)) {
                    continue;
                }
                FileUtils.copyAllFiles(sourceFile, destFile);
                if (isService) {
                    File file = new File(destFile + "/impl");
                    if (file.isDirectory()) {
                        FileUtils.deleteFile(destFile + "/impl");
                    }
                    isService = false;
                }
            }
        }
    }

    /**
     * 执行该方法前，请仔细阅读README.MD 文件中的说明
     */

    public static void main(String[] args) throws Exception {
        String pathdir = System.getProperty("user.dir") + File.separator + config.getProject() + File.separator + "out" + File.separator;
        BuildFactory.setLoadingDir(pathdir);
        Builder builder = new Builder();
        //1、创建java类
        builder.db2entry();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        System.out.println("工程格式化封装" + config.getPackagePath());

        //2、创建项目，3、创建各自子项目对应的pom.xml文件
        //4、向service项目文件中创建.properties文件
        //5、创建applicaion执行程序
        createProject();//创建项目
        //6、将java类和mybatis.xml文件放到对应的项目路径下
        moveFile2Project_new(pathdir);


    }


}
