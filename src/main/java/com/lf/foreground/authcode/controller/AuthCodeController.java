package com.lf.foreground.authcode.controller;

import com.lf.foreground.authcode.service.AuthCodeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by monst on 2019/6/20.
 * 生成验证码
 */
@Api(description = "生成验证码")
@RestController
@RequestMapping("auth")
public class AuthCodeController {

    //注入AuthCodeService
    @Autowired
    private AuthCodeService authCodeService;

    /**
     * 获取验证码
     */
    @GetMapping("randomNum")
    public void randomNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authCodeService.randomNum(request,response);
    }

}
