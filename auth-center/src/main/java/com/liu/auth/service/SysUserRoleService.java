package com.liu.auth.service;


import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysUserRole;

import java.util.Map;

public interface SysUserRoleService {
    /**
     * 删除
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增加
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    int insertSelective(SysUserRole SysUserRole);

    /**
     * 查询
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    SysUserRole selectByPrimaryKey(String id);

    /**
     * 分页查询
     * @param start 当前页起始值
     * @param length 每页显示数量
     * @param parameterMap 查询参数
     * @return  当前页List
     */
    PageInfo<SysUserRole> listSysRoleUsers(int start, int length, Map<String, Object> parameterMap);
    
}
