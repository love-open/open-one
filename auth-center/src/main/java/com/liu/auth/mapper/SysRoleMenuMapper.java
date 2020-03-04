package com.liu.auth.mapper;

import com.liu.auth.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    List<SysRoleMenu> listSysRoleMenus(Map<String, Object> parameterMap);
}