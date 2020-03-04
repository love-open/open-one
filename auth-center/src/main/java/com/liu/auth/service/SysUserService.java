package com.liu.auth.service;


import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysUser;

import java.util.Map;

public interface SysUserService {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysUser sysUsers);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser sysUsers);
    
    int updateByAccountSelective(SysUser sysUsers);
    /**
     * 分页查询
     * @param start 当前页起始值
     * @param length 每页显示数量
     * @param parameterMap 查询参数
     * @return  当前页List
     */
    PageInfo<SysUser> listSysUsers(int start, int length, Map<String, Object> parameterMap);
    
    /**
     * @author Frank.liu
     * @date 2019.10.19
     * @since 根据用户账号查询用户所在的部门
     * @param  username
     */
    String selectDepartmentByAccount(String username);
    
    /**
     * @author Frank.liu
     * @date 2019.10.21
     * @since 根据用户账号查询用户的主管部门
     * @param  username
     */
    String selectCompetentDepartmentByAccount(String username);

    /**
     *功能描述：根据用户的账号获取用户信息
     * @author Frank.liu
     * @date 2019/11/22
     * @param username
     * @return SysUser
     */
    SysUser selectByAccount(String username);
}
