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

    /**
     * 添加帖子
     * @param discussPost
     * @return
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     * 根据帖子id获取帖子信息
     * @param id
     * @return
     */
    DiscussPost selectDiscussPostById(int id);

    /**
     * 修改评论数量
     * @param id
     * @param commentCount
     * @return
     */
    int updateCommentCount(int id,int commentCount);
}
