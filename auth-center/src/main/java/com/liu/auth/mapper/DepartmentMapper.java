package com.liu.auth.mapper;


import com.liu.auth.domain.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    List<Department> getDepartmentsSelective(Department record);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}
