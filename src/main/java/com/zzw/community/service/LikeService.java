package com.zzw.community.service;

import com.zzw.community.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * 处理点赞的相关业务
 * @author 张志伟
 * @version v1.0
 */
@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 点赞
     * @param userId
     * @param entityType
     * @param entityId
     * @param entityUserId
     */
    public void like(int userId, int entityType, int entityId, int entityUserId){
        //注意，Redis的查询一定要在事务之外，因为事务内不会立刻得到结果
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);
                //判断是否点过赞
                Boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                //启用事务
                operations.multi();
                if (isMember){
                    //已经点过赞
                    //取消赞
                    operations.opsForSet().remove(entityLikeKey,userId);
                    //减少被赞人的一个赞
                    operations.opsForValue().decrement(userLikeKey);
                }else {
                    //点赞
                    operations.opsForSet().add(entityLikeKey,userId);
                    //增加被赞人的一个赞
                    operations.opsForValue().increment(userLikeKey);
                }
                //提交事务
                return operations.exec();
            }
        });
    }

    /**
     * 统计点赞的数量
     * @param entityType
     * @param entityId
     * @return
     */
    public long findEntityLikeCount(int entityType, int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    /**
     * 查询某人对某实体的点赞状态
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public int findEntityLikeStatus(int userId, int entityType, int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);

        //判断当前用户是否点过赞
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    /**
     * 查询某个用户获得的赞
     * @param userId
     * @return
     */
    public int findUserLikeCount(int userId){
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count.intValue();
    }

}
