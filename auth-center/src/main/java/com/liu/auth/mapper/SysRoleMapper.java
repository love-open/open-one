package com.liu.auth.mapper;

import com.liu.auth.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    List<SysRole> listSysRoles(Map<String, Object> parameterMap);

    List<SysRole> searchSysRole();
}