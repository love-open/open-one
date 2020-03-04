package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysRole;
import com.liu.auth.mapper.SysRoleMapper;
import com.liu.auth.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    SysRoleMapper sysRoleMapper;


    /**
     * 删除
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 添加
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int insertSelective(SysRole sysRole) {
        return sysRoleMapper.insertSelective(sysRole);
    }

    /**
     * 查询
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public SysRole selectByPrimaryKey(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int updateByPrimaryKeySelective(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    /**
     * 分页
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public PageInfo<SysRole> listSysRoles(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start, length);
        List<SysRole> listSysRole = sysRoleMapper.listSysRoles(parameterMap);
        PageInfo<SysRole> listSysRolePageInfo = new PageInfo<>(listSysRole);
        return listSysRolePageInfo;
    }
}
