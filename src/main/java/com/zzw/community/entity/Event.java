package com.zzw.community.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件
 * @author 张志伟
 * @version v1.0
 */
public class Event {
    /**
     * kafka的topic
     */
    private String topic;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 实体类型
     */
    private int entityType;
    /**
     * 实体Id
     */
    private int entityId;

    private int entityUserId;
    /**
     * 让数据具备可扩展性
     */
    private Map<String,Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key,Object value) {
        this.data.put(key,value);
        return this;
    }
}
