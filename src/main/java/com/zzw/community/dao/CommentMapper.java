package com.zzw.community.dao;

import com.zzw.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张志伟
 * @version v1.0
 */
@Mapper
@Repository
public interface CommentMapper {

    /**
     * 根据实体查询评论
     * @param entityType 实体类型
     * @param entityId 实体Id
     * @param offset 起始位置
     * @param limit 限制个数
     * @return
     */
    List<Comment> selectCommentsByEntity(int entityType,int entityId,int offset,int limit);

    /**
     * 根据实体查询评论的个数
     * @param entityType
     * @param entityId
     * @return
     */
    int selectCountByEntity(int entityType,int entityId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 根据id查找comment
     * @param id
     * @return
     */
    Comment selectCommentById(int id);

}
