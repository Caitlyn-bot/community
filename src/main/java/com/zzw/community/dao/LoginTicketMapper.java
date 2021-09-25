package com.zzw.community.dao;

import com.zzw.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 登录凭证的操作
 * @author 张志伟
 * @version v1.0
 */
@Mapper
@Repository
public interface LoginTicketMapper {

    /**
     * 添加凭证
     * @param loginTicket
     * @return
     */
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values (#{userId},#{ticket},#{status},#{expired}) "
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    /**
     * 根据ticket查询凭证信息
     * @param ticket
     * @return
     */
    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket = #{ticket} "
    })
    LoginTicket selectByTicket(String ticket);

    /**
     * 根据ticket修改凭证的状态
     * @param ticket
     * @param status
     * @return
     */
    @Update({
            "update login_ticket set status = #{status} ",
            "where ticket = #{ticket} "
    })
    int updateStatus(String ticket,int status);

}
