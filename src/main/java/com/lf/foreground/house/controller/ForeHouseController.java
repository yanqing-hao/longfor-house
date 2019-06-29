package com.lf.foreground.house.controller;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.foreground.house.service.ForeHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by monst on 2019/6/19.
 * 前台首页房源展示
 */
@Api(description = "房源管理模块")
@RestController
@RequestMapping("foreHouse")
@Slf4j
public class ForeHouseController {

    //注入ForeHouseService
    @Autowired
    private ForeHouseService foreHouseService;

    /**
     * 查询租赁房源
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("queryLeaseHouseList")
    @ApiOperation(value="查询租赁房源列表",notes = "查询租赁房源列表")
    public ModelAndView queryLeaseHouseList(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "rows",defaultValue = "4") Integer rows){
        ModelAndView modelAndView = new ModelAndView("foreground/house/lease-lise");
        PageInfo<HouseShow> houseStaffList = foreHouseService.queryLeaseHouseList(page,rows);
        modelAndView.addObject("leaseHouseList",houseStaffList);
        return modelAndView;
    }

    /**
     * 查询销售房源（新房）
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("queryNewHouseList")
    @ApiOperation(value="查询新房销售房源列表",notes = "查询新房销售房源列表")
    public ModelAndView queryNewHouseList(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "rows",defaultValue = "4") Integer rows){
        ModelAndView modelAndView = new ModelAndView("foreground/house/new-lise");
        PageInfo<HouseShow> houseList = foreHouseService.queryNewHouseList(page,rows);
        modelAndView.addObject("newHouseList",houseList);
        return modelAndView;
    }

    /**
     * 查询销售房源（二手房）
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("queryErHouseList")
    @ApiOperation(value="查询二手销售房源列表",notes = "查询二手销售房源列表")
    public ModelAndView queryErHouseList(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "rows",defaultValue = "4") Integer rows){
        ModelAndView modelAndView = new ModelAndView("foreground/house/old-lise");
        PageInfo<HouseShow> houseList = foreHouseService.queryErHouseList(page,rows);
        modelAndView.addObject("oldHouseList",houseList);
        return modelAndView;
    }
}
