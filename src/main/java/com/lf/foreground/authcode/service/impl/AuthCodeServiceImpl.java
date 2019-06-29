package com.lf.foreground.authcode.service.impl;

import com.lf.commons.RandomNumUtil;
import com.lf.foreground.authcode.service.AuthCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by monst on 2019/6/20.
 * 生成验证码
 */
@Service
@Transactional
public class AuthCodeServiceImpl implements AuthCodeService {

    private static final Integer WIDTH = 120;
    private static final Integer HEIGHT = 30;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     */
    @Override
    public void randomNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //1.设置背景颜色
        RandomNumUtil.setBackground(g, WIDTH, HEIGHT);
        //2.设置边框
        RandomNumUtil.setBorder(g, WIDTH, HEIGHT);
        //3.设置随机干扰线
        RandomNumUtil.drawRandomLine(g, WIDTH, HEIGHT);
        //4.随机生成四位验证码(数字,大小写字母)
        String randomNum = RandomNumUtil.drawNums((Graphics2D) g);
        //将生成的验证码放到session中,一边进行校验
        request.getSession().setAttribute("code", randomNum);
        //5.将图片写到浏览器上
        response.setContentType("image/jpeg");
        //控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
