package com.zzw.community;

import com.zzw.community.dao.DiscussPostMapper;
import com.zzw.community.dao.UserMapper;
import com.zzw.community.entity.DiscussPost;
import com.zzw.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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
}
