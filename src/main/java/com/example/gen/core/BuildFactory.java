package com.example.gen.core;


import com.example.gen.config.SetupConfig;
import com.example.gen.jdbc.AbstractDaoSupport;
import com.example.gen.util.MyUtils;
import com.example.gen.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liyiming
 * @date 14/10/24
 */
public class BuildFactory {
	private static  Map<String, Map<String, Object>> CACHE = new HashMap<String, Map<String, Object>>();
	private static SetupConfig config = SetupConfig.getInstance();
	private static AbstractDaoSupport dao = AbstractDaoSupport.getInstance();
	
	/**
	 * 配置属性
	 */
	private static Configuration cfg = new Configuration();

	/**
	 * 这里我设置模板的根目录
	 * @param dirPath 目录路径
	 */
	public static void setLoadingDir(String dirPath) {
		try {
			cfg.setDirectoryForTemplateLoading(new File(dirPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据模板生成文件
	 * 
	 * @param templateFile 
	 * @param obj 
	 * @param outFile 
	 */
	public void build(String templateFile, Object obj, String outFile) {
		try {
		    cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/" + config.getProject() + "/src/main/resources/template"));
			Template t = cfg.getTemplate(templateFile);
			Writer out = new OutputStreamWriter(new FileOutputStream(outFile), "utf-8");
			t.process(obj, out);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * POJO数据准备
	 * @param tableName 
	 * @return Map 
	 */
	public Map<String, Object> getParams(String tableName, String packagePath, String baseBeanPath) {
		if (CACHE.containsKey(tableName)) {
			Map<String, Object> map = CACHE.get(tableName);
			map.put("model_package", MyUtils.buildModelPackage(tableName));
			map.put("package_path", packagePath);
			map.put("baseBean_path", baseBeanPath);
			map.put("inner_path",config.getInnerPackagePath());
			map.put("crudService_path",config.getCrudServicePath());
			map.put("abstractCrudService_path",config.getAbstractCrudServicePath());
			map.put("crudDao_path",config.getCrudDaoPath());
			map.put("baseController_Path",config.getBaseControllerPath());
			map.put("respData_Path",config.getRespDataPath());
			map.put("uuid_Path",config.getUuidPath());
			return map;
		}
		// 数据准备,可以是Map,List或者是实体
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("package_path", packagePath);
		map.put("baseBean_path", baseBeanPath);
		map.put("inner_path",config.getInnerPackagePath());
		map.put("model_package", MyUtils.buildModelPackage(tableName));
		map.put("table_name", tableName);
		String className = StringUtil.className(tableName);
		map.put("class_name", className);
		map.put("param_name", className + "Param");	// liyiming
		List<Column> columns = dao.queryColumns(tableName);
		// 设置数据
		map.put("table_column", columns);
		// 特殊字符处理
		map.put("hasDateColumn", Column.typeContains(columns, "Date"));
		map.put("hasBigDecimalColumn", Column.typeContains(columns, "BigDecimal"));
		map.put("project", config.getProject());
		map.put("author", config.getAuthor());
		map.put("sysDate", new Date());
		map.put("projectName",config.getProjectName());
		map.put("crudService_path",config.getCrudServicePath());
		map.put("abstractCrudService_path",config.getAbstractCrudServicePath());
		map.put("crudDao_path",config.getCrudDaoPath());
        map.put("baseController_Path",config.getBaseControllerPath());
        map.put("respData_Path",config.getRespDataPath());
        map.put("uuid_Path",config.getUuidPath());
		CACHE.put(tableName, map);
		return map;
	}
}
