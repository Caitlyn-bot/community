package com.zzw.community;

import com.zzw.community.dao.DiscussPostMapper;
import com.zzw.community.dao.LoginTicketMapper;
import com.zzw.community.dao.UserMapper;
import com.zzw.community.entity.DiscussPost;
import com.zzw.community.entity.LoginTicket;
import com.zzw.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

/**
 * @author 张志伟
 * @version v1.0
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectUserById(101);
        System.out.println(user);
    }
    @Test
    public void testPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        int i = discussPostMapper.selectDiscussPostRows(0);
        System.out.println("总条数为"+i);
        System.out.println("第一页的数据为");
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }
    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        int abc = loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

}
