package com.liu.auth.service;

import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Department> getDepartmentsSelective(Department record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Department record);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据id更新公司属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Department record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param department
     * @return
     */
    PageInfo<Department> getDepartmentsPageSelective(Integer start, Integer length, Department department);

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    Department selectByPrimaryKey(String id);

    /**
     * code不存在时保存，code存在时更新数据
     * @param record
     * @return
     */
    int saveOrUpdate(Department record);
}
