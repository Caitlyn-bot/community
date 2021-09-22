package com.zzw.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author 张志伟
 * @version v1.0
 */
@Configuration
public class AlphaConfig {
    /**
     * 方法名就是bean的名字
     * @return
     */
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
