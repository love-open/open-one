package com.liu.auth.mapper;


import com.liu.auth.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysUser sysUsers);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser sysUsers);

    List<SysUser> listSysUsers(Map<String, Object> parameterMap);
    
    int updateByAccountSelective(SysUser sysUsers);
    
    /**
     * @author Frank.liu
     * @date 2019.10.19
     * @since 根据用户账号查询用户所在的部门
     * @param  username
     */
    String selectDepartmentByAccount(@Param(value = "username") String username);
    
    /**
     * @author Frank.liu
     * @date 2019.10.21
     * @since 根据用户账号查询用户的主管部门
     * @param  username
     */
    String selectCompetentDepartmentByAccount(@Param(value = "username") String username);

    /**
     *功能描述：根据用户的账号获取用户信息
     * @author Frank.liu
     * @date 2019/11/22
     * @param username
     * @return SysUser
     */
    SysUser selectByAccount(String username);
}
