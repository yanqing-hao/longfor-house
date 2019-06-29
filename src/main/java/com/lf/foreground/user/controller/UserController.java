package com.lf.foreground.user.controller;

import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.ResultVo;
import com.lf.foreground.user.domain.SeachUser;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.domain.UserTemp;
import com.lf.foreground.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by 代光磊 on 2019/6/13.
 */
@Controller
@Api(tags = "用户模块")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 分页 条件查询
     * @return
     */
    @ResponseBody
    @ApiOperation("分页 条件查询")
    @RequestMapping("queryList")
    public DataGrid<UserTemp> queryList(SeachUser sea, PageUtil page){
        LOGGER.info("条件sea{}  分页page{}",sea,page);
        return userService.queryList(sea,page);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @ApiOperation("添加用户信息")
    @RequestMapping("addUser")
    public String addUser(User user){
        LOGGER.info("user {}",user);
        return userService.addUser(user);
    }

    @ResponseBody
    @ApiOperation("根据id查询用户信息")
    @RequestMapping("selUserByid")
    public User selUserByid(Integer usId){
        LOGGER.info("根据id查询 usId {}",usId);
        return userService.selectUser(usId);
    }

    @ResponseBody
    @ApiOperation("修改用户信息")
    @RequestMapping("updateUser")
    public String updateUser(User user){
        LOGGER.info("修改用户信息 user {}",user);
        return userService.updateUser(user);
    }

    @ResponseBody
    @ApiOperation("删除用户信息")
    @RequestMapping("deleteUser")
    public String deleteUser(Integer userId){
        LOGGER.info("逻辑删除 userId {}",userId);
        return userService.deleteUser(userId);
    }

    /**
     * 手机唯一校验
     * @param userPhone
     * @return
     */
    @ResponseBody
    @ApiOperation("手机唯一校验")
    @GetMapping("checkUserPhone")
    public ResultVo checkUserPhone(String userPhone){
        LOGGER.info("手机号{}",userPhone);
        ResultVo resultVo = userService.checkUserPhone(userPhone);
        return resultVo;
    }

    /**
     * 发送短信(6位验证码)
     * @return
     */
    @ResponseBody
    @ApiOperation("发送短信")
    @GetMapping("sendMs")
    public ResultVo sendMs(HttpServletRequest request, String userPhone){
        ResultVo resultVo = userService.sendMs(request,userPhone);
        return resultVo;
    }

    /**
     * 校验验证码
     * @param request
     * @param authCode
     * @return
     */
    @ResponseBody
    @ApiOperation("校验手机验证码")
    @GetMapping("checkPhoneCode")
    public ResultVo checkPhoneCode(HttpServletRequest request, String authCode){
        ResultVo resultVo = userService.checkPhoneCode(request,authCode);
        return resultVo;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ResponseBody
    @ApiOperation("用户注册")
    @PostMapping("userRegist")
    public ResultVo userRegist(User user){
        ResultVo resultVo = userService.userRegist(user);
        return resultVo;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ResponseBody
    @ApiOperation("用户登录")
    @PostMapping("userLogin")
    public ResultVo userLogin(User user){
        ResultVo resultVo = userService.userLogin(user);
        return resultVo;
    }

    /**
     * 校验图片验证码
     * @param
     * @return
     */
    @ResponseBody
    @ApiOperation("校验图片验证码")
    @GetMapping("checkAuthCode")
    public ResultVo checkAuthCode(HttpServletRequest request,String picAuthCode){
        ResultVo resultVo = userService.checkAuthCode(request,picAuthCode);
        return resultVo;
    }









}
