package com.lf.interceptor;


import com.lf.background.staff.domain.Staff;
import com.lf.commons.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by monst on 2019/6/17.
 * Staff登录拦截器
 */
//@Component
public class StaffInterceptor implements HandlerInterceptor {

    //注入redis
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Staff staff = (Staff) redisUtil.get("staff");  // 判断登录 获取redis中的staff
        if(staff == null){
            //未登陆，返回登陆页面
            httpServletRequest.getRequestDispatcher("/skip/toStaffLogin").forward(httpServletRequest,httpServletResponse);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
