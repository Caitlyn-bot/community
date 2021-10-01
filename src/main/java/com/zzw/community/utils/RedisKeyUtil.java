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
     * 验证码
     */
    private static final String PREFIX_KAPTCHA = "kaptcha";
    /**
     * 登录凭证
     */
    private static final String PREFIX_TICKET = "ticket";
    /**
     * 用户信息
     */
    private static final String PREFIX_USER = "user";

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

    /**
     * 登录验证码
     * @param owner
     * @return
     */
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    /**
     * 登录凭证
     * @param ticket
     * @return
     */
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }

    /**
     * 用户信息
     * @param userId
     * @return
     */
    public static String getUserKey(int userId){
        return PREFIX_USER + SPLIT + userId;
    }

}
