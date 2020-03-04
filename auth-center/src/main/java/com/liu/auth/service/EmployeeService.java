package com.liu.auth.service;

import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Employee> getEmployeesSelective(Employee record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Employee record);

    /**
     * 根据id删除
     * @param code
     * @return
     */
    int deleteByPrimaryKey(String code);

    /**
     * 根据id更新公司属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Employee record);

    int saveOrUpdateEmployee(Employee record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param employee
     * @return
     */
    PageInfo<Employee> getEmployeesPageSelective(Integer start, Integer length, Employee employee);

    /**
     * 按主键查询
     * @param code
     * @return
     */
    Employee selectByPrimaryKey(String code);

    /**
     * 查询最大工号
     * @return
     */
    String selectMaxStaffNumber();
}
