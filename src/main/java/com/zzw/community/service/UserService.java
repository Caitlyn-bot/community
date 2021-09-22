package com.zzw.community.service;

import com.zzw.community.dao.UserMapper;
import com.zzw.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张志伟
 * @version v1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectUserById(id);
    }
}
