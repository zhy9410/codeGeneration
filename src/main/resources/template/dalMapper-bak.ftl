package ${package_path}.dao.${inner_path};

import ${package_path}.model.${inner_path}.${class_name};
import ${package_path}.param.${inner_path}.${param_name};
import cn.com.goldenwater.core.persistence.CrudDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${class_name}Dao extends CrudDao<${class_name}, ${param_name}> {


}