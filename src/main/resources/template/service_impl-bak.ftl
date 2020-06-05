package ${package_path}.service.impl.${inner_path};

import ${package_path}.dao.${inner_path}.${class_name}Dao;
import ${package_path}.model.${inner_path}.${class_name};
import ${package_path}.param.${inner_path}.${param_name};
import ${package_path}.service.${inner_path}.${class_name}Service;
import cn.com.goldenwater.core.service.AbstractCrudService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ${author}
 * @date ${sysDate?date}
 */
@Service
@Transactional
public class ${class_name}ServiceImpl extends AbstractCrudService<${class_name}, ${param_name}> implements ${class_name}Service {

    @Autowired
    private ${class_name}Dao ${class_name?uncap_first}Dao;

    public ${class_name}ServiceImpl(${class_name}Dao ${class_name?uncap_first}Dao) {
        super(${class_name?uncap_first}Dao);
        this.${class_name?uncap_first}Dao = ${class_name?uncap_first}Dao;
    }


}