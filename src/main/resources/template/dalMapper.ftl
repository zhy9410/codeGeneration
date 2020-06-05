package ${package_path}.dao;

import ${package_path}.model.${class_name};
import ${package_path}.param.${param_name};
import ${crudDao_path};
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${class_name}Dao extends CrudDao<${class_name}, ${param_name}> {


}