package com.lf.background.login.controller;

import com.lf.background.login.service.LoginService;
import com.lf.commons.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Zy on 2019/6/13.
 */
@Api(description = "员工登录")
@Controller
@RequestMapping("staffLogin")
public class LoginController {
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "login")
    @ResponseBody
    public ResultVo login(HttpServletRequest request,String loginName,String loginPass){

        ResultVo resultVo = loginService.getLoing(loginName,loginPass);
        return resultVo;

    }

    /**
     * 销售员工登录
     * @return
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    @ApiOperation(value = "注销员工登录",notes = "注销员工登录")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("/background/staffLogin");
        loginService.logout();
        return  modelAndView;
    }


}
