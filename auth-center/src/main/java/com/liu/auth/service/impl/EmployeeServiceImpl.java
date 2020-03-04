package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.Department;
import com.liu.auth.domain.Employee;
import com.liu.auth.mapper.EmployeeMapper;
import com.liu.auth.service.DepartmentService;
import com.liu.auth.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Employee> getEmployeesSelective(Employee record) {
        return employeeMapper.getEmployeesSelective(record);
    }

    @Override
    public int insertSelective(Employee record) {
        return employeeMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String userId) {
        return employeeMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return employeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int saveOrUpdateEmployee(Employee record){
        Employee employees = this.selectByPrimaryKey(record.getUserId());
        if(employees==null){
            return this.insertSelective(record);
        }else{
            return this.updateByPrimaryKeySelective(record);
        }
    }
    @Override
    public PageInfo<Employee> getEmployeesPageSelective(Integer start, Integer length, Employee employee) {
        PageHelper.offsetPage(start,length);
        List<Employee> employeeSelective = employeeMapper.getEmployeesSelective(employee);
        for (Employee employeeTmp : employeeSelective) {
            String[] departmentids = employeeTmp.getDepartmentId().split(",");
            String departmentName = "";
            for (String departmentid:departmentids) {
                Department departmentsre = departmentService.selectByPrimaryKey(departmentid.trim());
                if(departmentsre!=null){
                    departmentName += departmentsre.getName()+",";
                }
            }
            departmentName = departmentName.substring(0,departmentName.length()-1);
            Department departmentresult = new Department();
            departmentresult.setName(departmentName);
            employeeTmp.setDepartment(departmentresult);
        }
        PageInfo<Employee> employeePageInfo = new PageInfo<Employee>(employeeSelective);
        return employeePageInfo;
    }

    @Override
    public Employee selectByPrimaryKey(String code) {
        return employeeMapper.selectByPrimaryKey(code);
    }

    /**
     * 查询最大工号
     *
     * @return
     */
    @Override
    public String selectMaxStaffNumber() {
        return employeeMapper.selectMaxStaffNumber();
    }
}
