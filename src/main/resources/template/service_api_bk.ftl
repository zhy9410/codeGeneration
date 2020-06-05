package ${package_path}.service.${inner_path};

import ${package_path}.model.${class_name};
import ${package_path}.param.${param_name};
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ${author}
 * @date ${sysDate?date}
 */
@FeignClient(name = "gw-cloud-biz-rc-basic-service", path = "/biz/rc/basic")
public interface ${class_name}Service {

    // ------------------------- 默认方法 -------------------------
    /**
     * 按主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ${class_name} get(@PathVariable("id") String id);

    /**
     * 按条件查询
     * @param ${param_name?uncap_first}}
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ${class_name} getBy(${param_name} ${param_name?uncap_first});

    /**
     * 按条件查询列表
     * @param ${param_name?uncap_first}
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<${class_name}> findList(${param_name} ${param_name?uncap_first});

    /**
     * 按条件查询列表（分页查询-不包含分页信息）
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public List<${class_name}> findPage(${param_name} ${param_name?uncap_first});

    /**
     * 按条件查询列表（分页查询-包含分页信息）
     * @return
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public PageInfo<${class_name}> findPageInfo(${param_name} ${param_name?uncap_first});

    /**
     * 添加数据
     * @param ${class_name?uncap_first}
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public int insert(@RequestBody ${class_name} ${class_name?uncap_first});

    /**
     * 按主键更新数据
     *
     * @param ${class_name?uncap_first}
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public int update(@RequestBody ${class_name} ${class_name?uncap_first});

    /**
     * 按主键删除数据-物理删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable("id") String id);

    /**
     * 按主键删除数据-逻辑删除（更新FLAG_VALID=0）
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete-logic/{id}", method = RequestMethod.DELETE)
    public int deleteInFlag(@PathVariable("id") String id);

    /**
     * 按条件删除数据
     *
     * @param ${param_name?uncap_first}
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public int deleteBy(${param_name} ${param_name?uncap_first});


    // ------------------------- 自定方法 -------------------------


}