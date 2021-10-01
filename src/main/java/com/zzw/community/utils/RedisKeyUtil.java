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
     * 实体前缀
     */
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    /**
     * 用户名前缀
     */
    private static final String PREFIX_USER_LIKE = "like:user";
    /**
     * 我的关注前缀
     */
    private static final String PREFIX_FOLLOWEE = "followee";
    /**
     * 我的粉丝前缀
     */
    private static final String PREFIX_FOLLOWER = "follower";

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

    /**
     * 某个用户的赞
     * like:user:userId -> int
     * @param userId
     * @return
     */
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT +userId;
    }

    /**
     * 我的关注
     * followee:userId:entityType -> zset(entityId,now)
     * @param userId
     * @param entityType
     * @return
     */
    public static String getFolloweeKey(int userId , int entityType){
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    /**
     * 我的粉丝
     * follower:entityType:entityId -> zset(userId,now)
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollowerKey(int entityType , int entityId){
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

}
