package com.liu.auth.mapper;

import com.liu.auth.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);
    
    List<SysUserRole> listSysRoleUsers(Map<String, Object> parameterMap);
}