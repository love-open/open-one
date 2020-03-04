package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysMenu;
import com.liu.auth.mapper.SysMenuMapper;
import com.liu.auth.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysMenu sysMenu) {
        return sysMenuMapper.insertSelective(sysMenu);
    }

    @Override
    public SysMenu selectByPrimaryKey(String id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }


    @Override
    public PageInfo<SysMenu> listSysMenus(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start, length);
        List<SysMenu> listSysMenus = sysMenuMapper.listSysMenus(parameterMap);
        PageInfo<SysMenu> listSysMenuPageInfo = new PageInfo<>(listSysMenus);
        return listSysMenuPageInfo;
    }


}
