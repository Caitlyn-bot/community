package com.zzw.community.service;

import com.zzw.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 张志伟
 * @version v1.0
 */
@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    /**
     * @PostConstruct表示该方法会在构造器初始化后调用
     */
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    /**
     * @PreDestroy 在销毁对象之前调用该方法
     */
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }

}
