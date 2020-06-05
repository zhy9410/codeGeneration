package ${package_path}.model;

import java.io.Serializable;
<#if (hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>
<#if (hasDateColumn)>
import java.util.Date;
</#if>

import ${baseBean_path};
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* entity:${class_name}
*
* @author ${author}
* @date ${sysDate?date}
*/
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class ${class_name} extends BaseBean implements Serializable {

<#list table_column as c>
    <#if (c.remark?exists && c.remark!="")>
    // ${c.remark}
     @ApiModelProperty(value = "${c.remark}",name = "${c.nameJ}")
    </#if>
    private ${c.type} ${c.nameJ};
</#list>

    public ${class_name}() {
    }

    <#list table_column as c>
    public ${c.type} get${c.nameJ?cap_first}() {
        return ${c.nameJ};
    }
    public void set${c.nameJ?cap_first}(${c.type} ${c.nameJ}) {
        this.${c.nameJ} = ${c.nameJ};
    }
    </#list>

    @Override
    public String toString() {
        return "${class_name} [" + <#list table_column as c>"<#if (c_index>=1)>, </#if>${c.nameJ}=" +  ${c.nameJ}  + </#list> "]";
    }
}
