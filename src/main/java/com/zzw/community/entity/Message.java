package com.zzw.community.entity;

import java.util.Date;

/**
 * 消息
 * @author 张志伟
 * @version v1.0
 */
public class Message {
    private int id;
    /**
     * 哪个用户发送的
     */
    private int fromId;
    /**
     * 消息是发送给哪个用户的
     */
    private int toId;
    /**
     * 会话id，from和to两个用户中间以_分隔，让id小的在左边
     */
    private String conversationId;
    /**
     * 消息
     */
    private String content;

    private int status;

    private Date createTime;

    public Message() {
    }

    public Message(int fromId, int toId, String conversationId, String content, int status, Date createTime) {
        this.fromId = fromId;
        this.toId = toId;
        this.conversationId = conversationId;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
    }

    public Message(int id, int fromId, int toId, String conversationId, String content, int status, Date createTime) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.conversationId = conversationId;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", conversationId='" + conversationId + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}