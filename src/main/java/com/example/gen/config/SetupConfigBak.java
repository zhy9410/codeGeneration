package com.example.gen.config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author liyiming
 * @date 14/10/24
 */
public class SetupConfigBak {

	private static SetupConfigBak instance;
	/**
	 * project work dir
	 */
	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String SEPARATOR = File.separator;

	private String project;
	private String packagePath;
	//新增内层路径
	private String innerPackagePath;
	/**
	 * default 'xdev'
	 */
	private String author = "xdev";

	private String ignorePrefix;
	/**
	 *
	 * 拆分模块名称
	 * */
	private ProjectModule[] modules;
	/**
	 *
	 * 依赖顶级节点名称
	 * */
	private String parentProject;

	/**
	 * 父节点pom文件
	 * */
	private String projectPom;

	/**
	 *
	 * 新建父节点名称，项目名
	 * */
	private String projectName;
	private DbConfig dbConfig;
	private String templateDir;
	private TemplateMapping[] mappings;
	private Group[] groups;
	/**
	 *
	 * 文件路径
	 * */
	private String javaDirection;
	/**
	 *
	 * 资源路径
	 * */
	private String resourceDirection;

	/**
	 *
	 * 项目依赖包
	 * */
	private String depencePackage;

	public String getInnerPackagePath() {
		return innerPackagePath;
	}

	public void setInnerPackagePath(String innerPackagePath) {
		this.innerPackagePath = innerPackagePath;
	}

	public String getDepencePackage() {
		return depencePackage;
	}

	public void setDepencePackage(String depencePackage) {
		this.depencePackage = depencePackage;
	}

	public String getJavaDirection() {
		return javaDirection;
	}

	public void setJavaDirection(String javaDirection) {
		this.javaDirection = javaDirection;
	}

	public String getResourceDirection() {
		return resourceDirection;
	}

	public void setResourceDirection(String resourceDirection) {
		this.resourceDirection = resourceDirection;
	}

	public String getParentProject() {
		return parentProject;
	}

	public void setParentProject(String parentProject) {
		this.parentProject = parentProject;
	}

	public String getProjectPom() {
		return projectPom;
	}

	public void setProjectPom(String projectPom) {
		this.projectPom = projectPom;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public static String getUserDir() {
		return USER_DIR;
	}

	public static String getSeparator() {
		return SEPARATOR;
	}

	public String getProject() {
		return project;
	}

	public ProjectModule[] getModules() {
		return modules;
	}

	public void setModules(ProjectModule[] modules) {
		this.modules = modules;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIgnorePrefix() {
		return ignorePrefix;
	}

	public void setIgnorePrefix(String ignorePrefix) {
		this.ignorePrefix = ignorePrefix;
	}

	public DbConfig getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(DbConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public TemplateMapping[] getMappings() {
		return mappings;
	}

	public void setMappings(TemplateMapping[] mappings) {
		this.mappings = mappings;
	}

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	private static String loadJson() {
		StringBuilder sb = new StringBuilder("");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(SetupConfigBak.class.getResourceAsStream("/config.json")));
			String str = "";
			while ((str = in.readLine()) != null) {
				// 处理行注释
				int contentIndex = str.indexOf("///");
				if (contentIndex != -1) {
					str = str.substring(0, contentIndex);
				}
				sb.append(str);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("找不到配置文件:" + "config-ssm.json");
		}
		String s = sb.toString();
		return sb.toString();
	}

	public static SetupConfigBak getInstance() {
		if (instance == null) {
			instance = new Gson().fromJson(loadJson(), SetupConfigBak.class);
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(getInstance());
	}

	@Override
	public String toString() {
		return "SetupConfig{" +
				"project='" + project + '\'' +
				", packagePath='" + packagePath + '\'' +
				", author='" + author + '\'' +
				", ignorePrefix='" + ignorePrefix + '\'' +
				", dbConfig=" + dbConfig +
				", templateDir='" + templateDir + '\'' +
				", mappings=" + Arrays.toString(mappings) +
				", groups=" + Arrays.toString(groups) +
				'}';
	}
}
