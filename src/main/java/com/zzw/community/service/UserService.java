package com.zzw.community.service;

import com.zzw.community.dao.UserMapper;
import com.zzw.community.entity.LoginTicket;
import com.zzw.community.entity.User;
import com.zzw.community.utils.CommunityConstant;
import com.zzw.community.utils.CommunityUtil;
import com.zzw.community.utils.MailClient;
import com.zzw.community.utils.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 张志伟
 * @version v1.0
 */
@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;



    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public User findUserById(int id){
        User user = getCache(id);
        if (user == null){
            user = initCache(id);
        }
        return user;
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();

        //先对空值进行判断处理
        if (user == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空");
            return map;
        }

        //验证账号
        User u = userMapper.selectUserByName(user.getUsername());
        if (u != null){
            map.put("usernameMsg","该账号已存在");
            return map;
        }
        //验证邮箱
        u = userMapper.selectUserByEmail(user.getEmail());
        if (u != null){
            map.put("emailMsg","该邮箱已被注册!");
            return map;
        }

        //注册用户
        //生成salt提高安全性
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("https://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setCreateTime(new Date());

        userMapper.insertUser(user);

        //激活邮件
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        //http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url",url);
        String content = templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(),"激活账号",content);

        return map;
    }

    /**
     * 激活账户
     * @param userId
     * @param code
     * @return
     */
    public int activation(int userId,String code){
        User user = userMapper.selectUserById(userId);
        if (user.getStatus() == 1){
            return ACTIVATION_REPEAT;
        }else if (user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId,1);
            //清理缓存
            clearCache(userId);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

    /**
     *  登录
     * @param username
     * @param password
     * @param expiredSeconds 登录后多少秒过期
     * @return
     */
    public Map<String,Object> login(String username, String password, int expiredSeconds){
        Map<String,Object> map = new HashMap<>();
        //空值处理
        if (StringUtils.isBlank(username)){
            map.put("usernameMsg","用户名不能为空!");
            return map;
        }
        if (StringUtils.isBlank(password)){
            map.put("passwordMsg","密码不能为空!");
            return map;
        }

        //验证账号
        User user = userMapper.selectUserByName(username);
        if (user == null){
            map.put("usernameMsg","该账号不存在!");
            return map;
        }
        //验证状态
        if (user.getStatus() == 0){
            map.put("usernameMsg","该账号未激活!");
            return map;
        }
        //验证密码
        password = CommunityUtil.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)){
            map.put("passwordMsg","密码不正确!");
            return map;
        }
        //生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * expiredSeconds));
        //获取key
        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        //会自动把对象序列化成json字符串
        redisTemplate.opsForValue().set(redisKey,loginTicket);
        //返回ticket
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    /**
     * 退出登录
     * @param ticket
     */
    public void logout(String ticket){
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey,loginTicket);
    }

    /**
     * 获取登录凭证
     * @param ticket
     * @return
     */
    public LoginTicket findLoginTicket(String ticket){
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        return loginTicket;
    }

    /**
     * 修改头像地址
     * @param userId
     * @param headerUrl
     * @return
     */
    public int updateHeader(int userId,String headerUrl){
        int rows = userMapper.updateHeader(userId, headerUrl);
        if (rows > 0){
            clearCache(userId);
        }
        return rows;
    }

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    public int updatePassword(int userId,String password){
        return userMapper.updatePassword(userId, password);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findUserByName(String username){
        return userMapper.selectUserByName(username);
    }

    /**
     * 优先从缓存中取数据
     * @param userId
     * @return
     */
    private User getCache(int userId){
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (User)redisTemplate.opsForValue().get(redisKey);
    }

    private User initCache(int userId){
        //从数据库中获取数据
        User user = userMapper.selectUserById(userId);
        //获取key
        String redisKey = RedisKeyUtil.getUserKey(userId);
        //存活3600s
        redisTemplate.opsForValue().set(redisKey,user,3600, TimeUnit.SECONDS);
        return user;
    }

    private void clearCache(int userId){
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

}
