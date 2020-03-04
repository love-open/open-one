package com.liu.auth.mapper;

import com.liu.auth.domain.auth.Role;
import com.liu.auth.domain.auth.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Frank.liu
 * createAt: 2020/2/14
 */
@Mapper
public interface AuthMapper {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserDetail findByUsername(@Param("username") String username);

    /**
     * 创建新用户
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     * @param userId
     * @param roleId
     * @return
     */
    int insertRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 根据角色id查找角色
     * @param roleId
     * @return
     */
    Role findRoleById(@Param("roleId") String roleId);

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") String userId);
}
