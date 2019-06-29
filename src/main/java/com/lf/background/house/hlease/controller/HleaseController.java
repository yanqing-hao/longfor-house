package com.lf.background.house.hlease.controller;

import com.lf.background.house.hlease.service.HleaseService;
import com.lf.background.house.house.domain.House;
import com.lf.background.picture.domain.Picture;
import com.lf.commons.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by monst on 2019/6/9.
 * 发布租赁房源
 */
@RestController
@Api(description = "发布租赁房源")
@RequestMapping("hlease")
@Slf4j
public class HleaseController {

    @Autowired
    private HleaseService hleaseService;
    /**
     * 发布租赁房源(新增)
     * @param house
     * @return
     */
    @ApiOperation(value="发布租赁房源",notes = "发布租赁房源")
    @PostMapping("insert")
    public ResultVo insert(House house){
        log.info("对象={}",house);
        return hleaseService.insert(house);
    }

    /**
     * 修改租赁房源
     * @param house
     * @return
     */
    @ApiOperation(value="修改租赁房源",notes = "修改租赁房源")
    @PostMapping("update")
    public ResultVo updateLeaseHouse(House house,Integer picId,String picPath){
        log.info("对象={}",house);
        log.info("图片id={}",picId);
        log.info("图片地址={}",picPath);
        return hleaseService.updateLeaseHouse(house,picId,picPath);
    }

}
