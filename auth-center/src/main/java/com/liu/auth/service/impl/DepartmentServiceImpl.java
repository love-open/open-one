package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.Department;
import com.liu.auth.mapper.DepartmentMapper;
import com.liu.auth.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentsSelective(Department record) {
        return departmentMapper.getDepartmentsSelective(record);
    }

    @Override
    public int insertSelective(Department record) {
        return departmentMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String departmentId) {
        return departmentMapper.deleteByPrimaryKey(departmentId);
    }

    @Override
    public int updateByPrimaryKeySelective(Department record) {
        return departmentMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public PageInfo<Department> getDepartmentsPageSelective(Integer start, Integer length, Department department) {
        PageHelper.offsetPage(start,length);
        List<Department> departmentSelective = departmentMapper.getDepartmentsSelective(department);
        PageInfo<Department> departmentPageInfo = new PageInfo<Department>(departmentSelective);
        return departmentPageInfo;
    }

    @Override
    public Department selectByPrimaryKey(String id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(Department record) {
        Department department = this.selectByPrimaryKey(record.getDepartmentId());
        if(department == null){
            return this.insertSelective(record);
        }else{
            return this.updateByPrimaryKeySelective(record);
        }
    }
}
