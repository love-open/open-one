package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysRoleMenu;
import com.liu.auth.mapper.SysRoleMenuMapper;
import com.liu.auth.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 删除
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return sysRoleMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public int insertSelective(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insertSelective(sysRoleMenu);
    }

    /**
     * 查询
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Override
    public SysRoleMenu selectByPrimaryKey(String id) {
        return sysRoleMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<SysRoleMenu> listSysRoleMenus(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start, length);
        List<SysRoleMenu> listSysRoleMenus = sysRoleMenuMapper.listSysRoleMenus(parameterMap);
        PageInfo<SysRoleMenu> listSysRoleMenuPageInfo = new PageInfo<>(listSysRoleMenus);
        return listSysRoleMenuPageInfo;
    }


}
