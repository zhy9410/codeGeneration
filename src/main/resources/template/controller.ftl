package ${package_path}.controller;

import ${package_path}.model.${class_name};
import ${package_path}.param.${param_name};
import ${package_path}.service.${class_name}Service;
import ${baseController_Path};
import ${respData_Path};
import ${uuid_Path};
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


    @ApiOperation(value = "添加（success）")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public RespData insert(@ApiParam(name = "${class_name?uncap_first}", value = "${class_name}", required = true) @RequestBody ${class_name} ${class_name?uncap_first}) {
        if(${class_name?uncap_first}.getId() == null){
            String uuid = UuidUtil.uuid();  // 生成uuid
            ${class_name?uncap_first}.setId(uuid);
        }

        try {
            int result = ${class_name?uncap_first}Service.insert(${class_name?uncap_first});
            return RespData.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespData.error("参数异常");
        }
    }

    @ApiOperation(value = "根据ID删除（success）")
    @RequestMapping(value = "/del/{id}", method = {RequestMethod.DELETE})
    public RespData delete(@ApiParam(name = "id", value = "id", required = true) @PathVariable String id) {
        try {
            int result = ${class_name?uncap_first}Service.delete(id);
            return RespData.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespData.error("参数异常");
        }
    }

    @ApiOperation(value = "更新信息 主键Id为必填参数（success）")
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public RespData update(@ApiParam(name = "${class_name?uncap_first}", value = "${class_name}", required = true) @RequestBody ${class_name} ${class_name?uncap_first}) {
        Assert.notNull(${class_name?uncap_first}.getId(), "主键id为必填参数");
        try {
            int result = ${class_name?uncap_first}Service.update(${class_name?uncap_first});
            return RespData.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespData.error("参数异常");
        }
    }

    @ApiOperation(value = "获取（单表） (pageNum =0 pageSize =0 不分页 查全部)（success）")
    @RequestMapping(value = "/get", method = {RequestMethod.POST})
    public RespData listChild(@RequestBody ${param_name} p) {

        try {
            PageInfo<${class_name}> list = ${class_name?uncap_first}Service.findPageInfo(p);
    return RespData.success(list);
    } catch (Exception e) {
    logger.error(e.getMessage());
    return RespData.error("参数异常");
    }
    }

    @ApiOperation(value = "根据id获取（对象详情）(success)")
    @RequestMapping(value = "/getObj/{id}", method = {RequestMethod.GET})
    public RespData info(@ApiParam(name = "id", value = "id", required = true) @PathVariable String id) {

        try {
        ${class_name} info = ${class_name?uncap_first}Service.get(id);
        return RespData.success(info);
        } catch (Exception e) {
        logger.error(e.getMessage());
        return RespData.error("参数异常");
        }
    }
}