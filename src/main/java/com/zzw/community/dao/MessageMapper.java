package com.zzw.community.dao;

import com.zzw.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张志伟
 * @version v1.0
 */
@Mapper
@Repository
public interface MessageMapper {
    /**
     *
     * 查询当前用户的会话列表，针对每个会话只返回一条最新的私信
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<Message> selectConversations(int userId,int offset,int limit);

    /**
     * 查询当前用户的会话数量
     * @param userId
     * @return
     */
    int selectConversationCount(int userId);

    /**
     * 查询某个会话所包含的私信列表
     * @param conversationId
     * @param offset
     * @param limit
     * @return
     */
    List<Message> selectLetters(String conversationId,int offset,int limit);

    /**
     * 查询某个会话所包含的私信数量
     * @param conversationId
     * @return
     */
    int selectLetterCount(String conversationId);

    /**
     * 查询用户的未读私信
     * @param userId
     * @param conversationId 会话ID进行动态处理
     * @return
     */
    int selectLetterUnreadCount(int userId,String conversationId);

    /**
     * 新增消息
     * @param message
     * @return
     */
    int insertMessage(Message message);

    /**
     * 修改消息的状态
     * @param ids
     * @param status
     * @return
     */
    int updateStatus(List<Integer> ids,int status);
}
