package com.liu.auth.mapper;


import com.liu.auth.domain.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDictTypeMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(SysDictType sysDictType);

    SysDictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictType sysDictType);

    List<SysDictType> listSysDictTypes(Map<String, Object> parameterMap);

    List<SysDictType> getDictByParams(Map<String, Object> params);
}
