package ${package_path}.param;

import io.swagger.annotations.ApiModelProperty;
import me.zhengjie.modules.base.param.PageParam;
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
     @ApiModelProperty(value = "${c.remark}",name = "${c.nameJ}")
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
