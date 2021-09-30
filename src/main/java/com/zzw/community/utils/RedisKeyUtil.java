package com.zzw.community.utils;

/**
 * 生成Redis的key
 * @author 张志伟
 * @version v1.0
 */
public class RedisKeyUtil {
    /**
     * 分隔符
     */
    private static final String SPLIT = ":";
    /**
     * 前缀
     */
    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    /**
     * 某个实体的赞
     * like:entity:entityType:entityId -> set(userId)
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

}
