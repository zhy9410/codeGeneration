package ${package_path}.controller.${inner_path};

import ${package_path}.model.${inner_path}.${class_name};
import ${package_path}.param.${inner_path}.${param_name};
import ${package_path}.service.${inner_path}.${class_name}Service;
import cn.com.goldenwater.core.web.BaseController;
import cn.com.goldenwater.core.web.BaseResponse;
import cn.com.goldenwater.id.util.UuidUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${author}
 * @date ${sysDate?date}
 */
@Api(value = "xxx管理",tags="xxx管理")
@RestController
@RequestMapping("/api")
public class ${class_name}Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ${class_name}Service ${class_name?uncap_first}Service;


    @ApiOperation(value = "添加xxx")
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject insert(@ApiParam(name = "${class_name?uncap_first}", value = "${class_name}", required = true) @RequestBody ${class_name} ${class_name?uncap_first}) {
        boolean success = false;
        JSONObject json = new JSONObject();
        String uuid = UuidUtil.uuid();  // 生成uuid
        String msg = "操作失败";
        ${class_name?uncap_first}.setId(uuid);
        try {
            int result = ${class_name?uncap_first}Service.insert(${class_name?uncap_first});
            success = true;
            msg = "操作成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("Message", msg);
        json.put("IsSuccess", success);
        return json;
    }

    @ApiOperation(value = "根据ID删除xxx")
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject delete(@ApiParam(name = "id", value = "id", required = true) @PathVariable String id) {
        boolean success = false;
        JSONObject json = new JSONObject();
        String uuid = UuidUtil.uuid();  // 生成uuid
        String msg = "操作失败";
        try {
            int result = ${class_name?uncap_first}Service.delete(id);
            success = true;
            msg = "操作成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("Message", msg);
        json.put("IsSuccess", success);
        return json;
    }

    @ApiOperation(value = "更新xxx信息")
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject update(@ApiParam(name = "${class_name?uncap_first}", value = "${class_name}", required = true) @RequestBody ${class_name} ${class_name?uncap_first}) {
        Assert.notNull(${class_name?uncap_first}.getId(), "主键id为必填参数");
        boolean success = false;
        JSONObject json = new JSONObject();
        String msg = "操作失败";
        try {
            int result = ${class_name?uncap_first}Service.update(${class_name?uncap_first});
            success = true;
            msg = "操作成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("Message", msg);
        json.put("IsSuccess", success);
        return json;
    }

    @ApiOperation(value = "获取xxx（单表）")
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject list(@RequestBody ${param_name} p) {

        JSONObject json = new JSONObject();
        try {
            PageInfo<${class_name}> list = ${class_name?uncap_first}Service.findPageInfo(p);
            json.put("resultList", list.getList());
            json.put("TotalCount", list.getTotal());
            json.put("IsSuccess", true);
            json.put("Message", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("IsSuccess", false);
            json.put("Message", "操作失败");
        }

        return json;
    }

	@ApiOperation(value = "根据id获取xxx（对象详情）")
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject info(@ApiParam(name = "id", value = "id", required = true) @PathVariable String id) {

        JSONObject json = new JSONObject();
        try {
            ${class_name} info = ${class_name?uncap_first}Service.get(id);
            json.put("IsSuccess", true);
            json.put("Message", "操作成功");
            json.put("info",info);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("IsSuccess", false);
            json.put("Message", "操作失败");
        }

        return json;
    }

}