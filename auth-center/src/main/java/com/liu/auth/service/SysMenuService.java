package com.liu.auth.service;


import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysMenu;

import java.util.Map;


public interface SysMenuService {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysMenu sysMenu);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu sysMenu);


    /**
     * 分页查询
     *
     * @param start        当前页起始值
     * @param length       每页显示数量
     * @param parameterMap 查询参数
     * @return 当前页List
     */
    PageInfo<SysMenu> listSysMenus(int start, int length, Map<String, Object> parameterMap);


}
