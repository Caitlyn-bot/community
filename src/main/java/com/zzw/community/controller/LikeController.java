package com.zzw.community.controller;

import com.zzw.community.entity.User;
import com.zzw.community.service.LikeService;
import com.zzw.community.utils.CommunityUtil;
import com.zzw.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张志伟
 * @version v1.0
 */
@Controller
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 点赞
     * @param entityType
     * @param entityId
     * @return
     */
    @RequestMapping(path = "/like",method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId){
        User user = hostHolder.getUser();

        //点赞
        likeService.like(user.getId(), entityType, entityId);
        //点赞数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        //状态：是否点赞
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        //返回的结果
        Map<String,Object> map = new HashMap<>();
        map.put("likeCount",likeCount);
        map.put("likeStatus",likeStatus);
        return CommunityUtil.getJSONString(0,null,map);
    }

}