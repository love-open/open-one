package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysUserRole;
import com.liu.auth.mapper.SysUserRoleMapper;
import com.liu.auth.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 删除
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int insertSelective(SysUserRole SysUserRole) {
        return sysUserRoleMapper.insertSelective(SysUserRole);
    }

    /**
     * 查询
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public SysUserRole selectByPrimaryKey(String id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 关联查询
     */
    @Override
    public PageInfo<SysUserRole> listSysRoleUsers(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start, length);
        List<SysUserRole> listSysRoleUserss = sysUserRoleMapper.listSysRoleUsers(parameterMap);
        PageInfo<SysUserRole> listSysRoleUsersPageInfo = new PageInfo<>(listSysRoleUserss);
        return listSysRoleUsersPageInfo;
    }



}
