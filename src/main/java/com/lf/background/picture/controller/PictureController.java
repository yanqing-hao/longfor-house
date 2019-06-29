package com.lf.background.picture.controller;

import com.lf.background.picture.domain.Picture;
import com.lf.background.picture.service.PictureService;
import com.lf.commons.ResultVo;
import com.lf.commons.UpFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monst on 2019/6/12.
 */
@Api(description = "图片模块")
@RestController
@RequestMapping("picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Picture.class);

    /**
     * 发布房源时
     * 上传图片
     * @return
     */
    @ApiOperation(value="上传图片",notes = "上传图片")
    @PostMapping("uploadFile")
   /* public Map<String,String> uploadFile(MultipartFile img, HttpServletRequest request){
        Map<String,String> map = new HashMap<String,String>();
        String url = UpFile.upFile(img, request, "images");
        LOGGER.info("图片名称img={}",img);
        map.put("url",url);
        return map;
    }*/
    public ResultVo uploadFile(MultipartFile img, HttpServletRequest request){
        LOGGER.info("需上传图片进入img={}",img);
        String string = UpFile.upFile(img, request, "images");
        ResultVo vo = new ResultVo();
        vo.setUrl(string);
        return vo;
    }

//    @Scheduled(cron = "0 0/10 * * * ?")   //每10秒触发一次
//    public void clearList(){
//        url.clear();
//    }

    /**
     * 添加图片
     * @param picture
     */
    @ApiOperation(value="添加图片",notes = "添加图片")
    @PostMapping("insert")
    public void insertPictures(Picture picture){
        pictureService.pictureService(picture);
    }

}
