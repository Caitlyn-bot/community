package com.zzw.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 张志伟
 * @version v1.0
 */
@Repository
public class AlphaDaoImpl implements AlphaDao{
    @Override
    public String select() {
        return "alpha";
    }
}
