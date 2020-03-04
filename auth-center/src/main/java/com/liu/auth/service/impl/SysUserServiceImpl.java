package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysUser;
import com.liu.auth.mapper.SysUserMapper;
import com.liu.auth.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysUser sysUsers) {
        return sysUserMapper.insertSelective(sysUsers);
    }

    @Override
    public SysUser selectByPrimaryKey(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(SysUser sysUsers) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUsers);
    }
    @Override
    public PageInfo<SysUser> listSysUsers(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start,length);
        List<SysUser> listSysUsers = sysUserMapper.listSysUsers(parameterMap);
        PageInfo<SysUser> listSysUsersPageInfo = new PageInfo<>(listSysUsers);
        return listSysUsersPageInfo;
    }

	@Override
	public int updateByAccountSelective(SysUser sysUsers) {
		return sysUserMapper.updateByAccountSelective(sysUsers);
	}

	@Override
	public String selectDepartmentByAccount(String username) {
		return sysUserMapper.selectDepartmentByAccount(username);
	}
	
	@Override
	public String selectCompetentDepartmentByAccount(String username) {
		return sysUserMapper.selectCompetentDepartmentByAccount(username);
	}

    @Override
    public SysUser selectByAccount(@Param(value = "username") String username) {
        return sysUserMapper.selectByAccount(username);
    }
}
