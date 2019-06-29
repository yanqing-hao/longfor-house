package com.lf.getredis;

import com.lf.background.staff.domain.Staff;
import com.lf.commons.RedisUtil;
import com.lf.foreground.user.domain.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by monst on 2019/6/15.
 * 获取登录状态
 */
@RestController
@Api(description = "获取redis中的对象")
@RequestMapping("redis")
public class DoRedis {

    //注入redisUtil
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取redis中staff
     * @return
     */
    @GetMapping("getStaff")
    public Staff getRedis(){
        Staff staff = (Staff) redisUtil.get("staff");
        return staff;
    }

    /**
     * 清除redis中staff
     * @return
     */
    @GetMapping("clearStaff")
    public void clearStaff(){
        redisUtil.remove("staff");
    }

    /**
     * 获取redis中user
     * @return
     */
    @GetMapping("getUser")
    public User getUser(){
        User user = (User) redisUtil.get("user");
        return user;
    }

    /**
     * 清除redis中user
     * @return
     */
    @GetMapping("clearUser")
    public void clearUser(){
        redisUtil.remove("user");
    }

    /**
     * 注销用户登录
     * @return
     */
    @GetMapping("logout")
    public void logout(){
        redisUtil.remove("user");
    }
}
