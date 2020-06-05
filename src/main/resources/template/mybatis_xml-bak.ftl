<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_path}.dao.${inner_path}.${class_name}Dao">

	<resultMap type="${package_path}.model.${inner_path}.${class_name}" id="${class_name?uncap_first}ResultMap">
		<#list table_column as c>
		<result property="${c.nameJ}" column="${c.name?upper_case}"/>
		</#list>
	</resultMap>
	
	<sql id="table_columns">
		<#list table_column as c>
		${c.name?upper_case}<#if c_has_next>,</#if>
		</#list>
    </sql>
	<sql id="entity_properties">
		<#list table_column as c>
		${r"#"}{${c.nameJ}}<#if c_has_next>,</#if>
		</#list>
	</sql>

    <!-- 使用like用法：columnName like concat('%',#columnName#,'%') -->
    <sql id="page_where">
        <trim prefix="where" prefixOverrides="and | or ">
		<#list table_column as c><#if (c_index>=1)>
            <if test="${c.nameJ} != null<#if (c.type!='Boolean' && c.type!='Date')> and ${c.nameJ} != ''</#if>">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
		</#if></#list>
        </trim>
    </sql>


    <select id="get" resultMap="${class_name?uncap_first}ResultMap" parameterType="String" >
        select <include refid="table_columns" /> from ${table_name} where ID = ${r"#"}{id}
    </select>

    <select id="getBy" resultMap="${class_name?uncap_first}ResultMap">
        select <include refid="table_columns" /> from ${table_name} <include refid="page_where" />
    </select>

    <select id="findAll" resultMap="${class_name?uncap_first}ResultMap">
        select <include refid="table_columns" /> from ${table_name}
    </select>

    <select id="findList" resultMap="${class_name?uncap_first}ResultMap">
        select <include refid="table_columns" /> from ${table_name} <include refid="page_where" />
    </select>

    <select id="selectCount" resultType="int" >
        select count(ID) from ${table_name} <include refid="page_where" />
    </select>


    <insert id="insert" parameterType="${package_path}.model.${inner_path}.${class_name}">
		insert into ${table_name}( <include refid="table_columns" /> ) 
		values ( <include refid="entity_properties" /> )
	</insert>

    <delete id="delete" parameterType="java.lang.String">
        delete from ${table_name} where ID = ${r"#"}{id}
    </delete>

	<delete id="deleteBy" parameterType="${package_path}.model.${inner_path}.${class_name}">
		delete from ${table_name} <include refid="page_where" />
	</delete>

    <update id="deleteInFlag" parameterType="java.lang.String">
        update ${table_name} set flag_valid = 0 where ID = ${r"#"}{id}
    </update>

	<update id="update" parameterType="${package_path}.model.${inner_path}.${class_name}">
		update ${table_name} 
		<trim prefix="set" suffixOverrides=",">
		<#list table_column as c><#if (c_index>=1)>
			<if test="${c.nameJ} != null<#if (c.type!='Boolean' && c.type!='Date')> and ${c.nameJ} != ''</#if>">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
		</#if></#list>
		</trim>
		<where>ID = ${r"#"}{id}</where>
	</update>

    <update id="updateBy" parameterType="${package_path}.model.${inner_path}.${class_name}">
        update ${table_name}
        <trim prefix="set" suffixOverrides=",">
		<#list table_column as c><#if (c_index>=1)>
            <if test="${c.nameJ} != null<#if (c.type!='Boolean' && c.type!='Date')> and ${c.nameJ} != ''</#if>">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
		</#if></#list>
        </trim>
        <include refid="page_where" />
    </update>


	<!-- 其他自定义SQL -->


</mapper>