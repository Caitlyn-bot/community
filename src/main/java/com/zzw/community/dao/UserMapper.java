package com.zzw.community.dao;

import com.zzw.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * @author 张志伟
 * @version v1.0
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    User selectUserByName(String name);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    User selectUserByEmail(String email);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户id修改用户的状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(int id,int status);

    /**
     * 根据id修改头像
     * @param id
     * @param headerUrl
     * @return
     */
    int updateHeader(int id,String headerUrl);

    /**
     * 根据id修改密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(int id,String password);
}
