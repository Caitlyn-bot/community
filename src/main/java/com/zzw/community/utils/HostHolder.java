package com.zzw.community.utils;

import com.zzw.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户的信息，用于代替session对象，以避免多线程下使用出现冲突
 * @author 张志伟
 * @version v1.0
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear() {
        users.remove();
    }

}
