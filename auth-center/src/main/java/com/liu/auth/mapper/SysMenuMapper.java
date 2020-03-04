package com.liu.auth.mapper;

import com.liu.auth.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    List<SysMenu> listSysMenus(Map<String, Object> parameterMap);


}