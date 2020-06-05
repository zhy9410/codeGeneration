package ${package_path}.param.${inner_path};

import cn.com.goldenwater.core.param.PageParam;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

/**
* ${class_name}Param
*
* @author ${author}
* @date ${sysDate?date}
*/
public class ${class_name}Param extends PageParam implements Serializable {

<#list table_column as c>
    <#if (c.remark?exists && c.remark!="")>
    // ${c.remark}
    @ApiParam(name = "${c.remark}")
    </#if>
    private ${c.type} ${c.nameJ};
</#list>

    public ${class_name}Param() {
    }

    <#list table_column as c>
    public ${c.type} get${c.nameJ?cap_first}() {
        return ${c.nameJ};
    }
    public void set${c.nameJ?cap_first}(${c.type} ${c.nameJ}) {
        this.${c.nameJ} = ${c.nameJ};
    }
    </#list>

}
