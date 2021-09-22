package com.zzw.community.dao;

import com.zzw.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张志伟
 * @version v1.0
 */
@Mapper
@Repository
public interface DiscussPostMapper {
    /**
     * 根据发帖人id分页查询帖子
     * @param userId 发帖人id
     * @param offset 分页起始位置
     * @param limit 限制显示条数
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    /**
     * 根据发帖人id查询该人帖子总数
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId")int userId);

}
