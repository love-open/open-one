package com.liu.auth.service;


import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysRole;

import java.util.Map;

public interface SysRoleService {
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
    int insertSelective(SysRole sysRole);

    /**
     * 查询
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    SysRole selectByPrimaryKey(String id);

    /**
     * 修改
     *
     * @author Frank.liu
     * @date 2019-8-22
     */
    int updateByPrimaryKeySelective(SysRole sysRole);

    /**
     * 分页查询
     *
     * @param start        当前页起始值
     * @param length       每页显示数量
     * @param parameterMap 查询参数
     * @return 当前页List
     */
    PageInfo<SysRole> listSysRoles(int start, int length, Map<String, Object> parameterMap);

   
}
