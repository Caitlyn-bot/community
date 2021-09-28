package com.zzw.community.entity;

import java.util.Date;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Comment {
    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String content;
    private Date createTime;

    public Comment() {
    }

    public Comment(int userId, int entityType, int entityId, int targetId, String content, Date createTime) {
        this.userId = userId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.targetId = targetId;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment(int id, int userId, int entityType, int entityId, int targetId, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.targetId = targetId;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", targetId=" + targetId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
