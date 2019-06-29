package com.lf.foreground.oldsellhouse.controller;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.sellhouse.service.SellService;
import com.lf.commons.PageUtil;
import com.lf.foreground.oldsellhouse.service.ProService;
import com.lf.foreground.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by Zy on 2019/6/17.
 */
@Controller
@RequestMapping("pro")
public class ProController {
    @Autowired
    private ProService proService;
    @Autowired
    private SellService sellService;
    @RequestMapping("queryProList")
    public String queryProList(Integer proSearch, @RequestParam(defaultValue = "1",name="pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "5",name = "pageSize") Integer pageSize, PageUtil pageUtil,ModelMap modelMap){
        //去找list展示页面
        //ModelAndView modelAndView = new ModelAndView("/foreground/oldsellhouse/oldsellhouse-list");
        PageInfo<HouseShow> list = proService.queryProList(proSearch,pageNum,pageSize,pageUtil);
        //List <HouseStaffPicture> list = proService.queryProList(proSearch);
        modelMap.addAttribute("list",list);
        return "/foreground/oldsellhouse/oldsellhouse-list";
    }

    //详情页查询
    @RequestMapping("queryProId")
    @ResponseBody
    public ModelAndView queryProId(Integer houseId){
        ModelAndView modelAndView = new ModelAndView("/foreground/oldsellhouse/oldsellhouse-proid");
        HouseStaffPicture houseStaffPicture = proService.queryProId(houseId);
        modelAndView.addObject("list",houseStaffPicture);
        return modelAndView;
    }
    //回显
    @RequestMapping("addUpdate")
    @ResponseBody
    public ModelAndView addUpdate(Integer houseId){
        ModelAndView modelAndView = new ModelAndView("/foreground/oldsellhouse/oldsellhouse-addUpdate");
        HouseStaffPicture houseStaffPicture = proService.addUpdate(houseId);
        modelAndView.addObject("houseStaffPicture",houseStaffPicture);
        return modelAndView;
    }



    //添加
    @RequestMapping("addPro")
    @ResponseBody
    public void addPro(HouseStaffPicture houseStaffPicture, User user){
        proService.addPro(houseStaffPicture,user);
    }
}
