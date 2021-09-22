package com.zzw.community.entity;

import java.util.Date;

/**
 *  发帖信息
 * @author  张志伟
 * @version v1.0
 */
public class DiscussPost {
    /**
     * 帖子的id
     */
    private int id;
    /**
     * 发帖人的id
     */
    private int userId;
    /**
     * 帖子的标题
     */
    private String title;
    /**
     *
     */
    private String content;
    /**
     * 帖子类型，0表示正常，1表示置顶
     */
    private int type;
    /**
     *  帖子状态，0表示正常，1表示精华，2表示拉黑
     */
    private int status;
    /**
     * 帖子创建的日期
     */
    private Date createTime;
    /**
     * 评论的数量
     */
    private int commentCount;
    /**
     * 帖子的评分
     */
    private double score;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DiscussPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }
}
