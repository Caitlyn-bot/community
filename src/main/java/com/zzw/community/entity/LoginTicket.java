package com.zzw.community.entity;

import java.util.Date;

/**
 * 登录凭证
 * @author 张志伟
 * @version v1.0
 */
public class LoginTicket {
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 登录凭证
     */
    private String ticket;
    /**
     * 登录状态，0表示正常，1表示失效
     */
    private int status;
    /**
     * 登录到期的时间
     */
    private Date expired;

    public LoginTicket() {
    }

    public LoginTicket(int userId, String ticket, int status, Date expired) {
        this.userId = userId;
        this.ticket = ticket;
        this.status = status;
        this.expired = expired;
    }

    public LoginTicket(int id, int userId, String ticket, int status, Date expired) {
        this.id = id;
        this.userId = userId;
        this.ticket = ticket;
        this.status = status;
        this.expired = expired;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticket='" + ticket + '\'' +
                ", status=" + status +
                ", expired=" + expired +
                '}';
    }
}
