package com.lf.background.house.hsell.controller;

import com.lf.background.house.house.domain.House;
import com.lf.background.house.hsell.service.HsellServive;
import com.lf.commons.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by monst on 2019/6/11.
 */
@RestController
@Api(description = "发布销售房源")
@RequestMapping("hsell")
public class HsellController {

    @Autowired
    private HsellServive hsellServive;
    private static final Logger LOGGER = LoggerFactory.getLogger(HsellController.class);
    /**
     * 发布销售房源(新增)
     * @param house
     * @return
     */
    @ApiOperation(value="发布销售房源",notes = "发布销售房源")
    @PostMapping("insert")
    public ResultVo insert(House house){
        LOGGER.info("对象={}",house);
        return hsellServive.insert(house);
    }


    /**
     * 修改销售房源
     * @param house
     * @return
     */
    @ApiOperation(value="修改销售房源",notes = "修改销售房源")
    @PostMapping("update")
    public ResultVo updateSellHouse(House house,Integer picId,String picPath){
        LOGGER.info("对象={}",house);
        LOGGER.info("图片id={}",picId);
        LOGGER.info("图片路径={}",picPath);
        return hsellServive.updateLeaseHouse(house,picId,picPath);
    }
}
