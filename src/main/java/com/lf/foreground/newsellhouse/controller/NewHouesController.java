package com.lf.foreground.newsellhouse.controller;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.commons.PageUtil;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import com.lf.foreground.newsellhouse.service.NewHouseService;
import com.lf.foreground.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 代光磊 on 2019/6/16.
 */
@Controller
@Api(tags = "前台新房销售模块")
@RequestMapping("newHouse")
public class NewHouesController {

    @Autowired
    private NewHouseService newHouseService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NewHouesController.class);

 /*   @ApiOperation("条件 分页查询")
    @RequestMapping("queryNewLlist")
    public ModelAndView queryNewLlist(NewSearch sea, PageUtil page){
        LOGGER.info("条件 sea {} 分页排序page {}",sea,page);
        ModelAndView mav = new ModelAndView("/foreground/newsellhouse/newsellhouse-mian");
        List<HouseStaffPicture> newList = newHouseService.queryNewList(sea,page);
        System.out.println(newList);
        mav.addObject("newList",newList);
        return mav;
    }*/
    @ApiOperation("条件 分页查询")
    @RequestMapping("queryNewLlist")
    public String queryNewLlist(NewSearch sea, @RequestParam(defaultValue="1",name="pageNum") Integer pageNum,@RequestParam(defaultValue="5",name="pageSize") Integer pageSize,PageUtil newPage, ModelMap map){
        LOGGER.info("条件 sea {} 每页条数pageSize {} 起始页{} newPage {}",sea,pageSize,pageNum,newPage);
        PageInfo<HouseShow> newList = newHouseService.queryNewList(sea,pageNum,pageSize,newPage);
        map.addAttribute("newList",newList);
        return "/foreground/newsellhouse/newsellhouse-list";
    }

    @ApiOperation("详情查询")
    @RequestMapping("selectAll")
    public ModelAndView selectAll(Integer houseId){
        LOGGER.info("id = {}" ,houseId);
        ModelAndView mav = new ModelAndView("/foreground/newsellhouse/newsellhouse-listAll");
        List<HouseStaffPicture> list = newHouseService.selectNewAll(houseId);
        mav.addObject("listAll",list);
        LOGGER.info("list= {}" ,list);
        return mav;
    }


    @ApiOperation("购买时的查询")
    @RequestMapping("buyHouse")
    public ModelAndView buyHouse(Integer houseId){
        LOGGER.info("houseId {}" , houseId);
        ModelAndView mav = new ModelAndView("/foreground/newsellhouse/newsellHouse-buy");
        House house = newHouseService.buyHouse(houseId);
        LOGGER.info("返回 house{}" , house);
        mav.addObject("house",house);
        return mav;
    }

    @ResponseBody
    @ApiOperation("确认购买房屋")
    @RequestMapping("newHouseDown")
    public void newHouseDown(User user,House house){
        LOGGER.info("用户{}  房源 {}",user,house);
        newHouseService.newHouseDown(user,house);
    }


}

