package com.lf.foreground.leasehouse.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.commons.DataGrid;
import com.lf.commons.ResultVo;
import com.lf.foreground.leasehouse.domain.OrderByVo;
import com.lf.foreground.leasehouse.domain.pageVo;
import com.lf.foreground.leasehouse.service.AgoLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

/**
 * Created by ASUS on 2019/6/17.
 */
@Controller
@RequestMapping("agoLeaseController")
public class AgoLeaseController {
    @Autowired
    private AgoLeaseService agoLeaseService;
/*条件查询*/
    @RequestMapping("agoLeaseList")
    public ModelAndView agoLeaseList(Integer state, pageVo vo, OrderByVo orvo) {
        ModelAndView mav=new ModelAndView();


        PageInfo<HouseShow> pageInfo= agoLeaseService.agoLeaseList(state,vo.getPageNum(),vo.getPageSize(),orvo);


        mav.addObject("vo1",vo);
        mav.addObject("PageInfo",pageInfo);
        mav.setViewName("/foreground/leasehouse/leaseList_zu");
        return mav;
    }
/* 详情  点击标题回显数据*/
    @RequestMapping("agoParticulars")

    public ModelAndView agoParticulars(HouseStaffPicture hsp){
        ModelAndView mav=new ModelAndView("/foreground/leasehouse/lease_particulars_ago");
        hsp= agoLeaseService.agoParticulars(hsp.getHouseId());
       mav.addObject("hsp",hsp);
        return mav;
    }
/*购买回显使用*/
@RequestMapping("agoParticularsPurchase")

    public ModelAndView agoParticularsPurchase(HouseStaffPicture hsp){
        ModelAndView mav=new ModelAndView("/foreground/leasehouse/lease_lease");
        hsp= agoLeaseService.agoParticulars(hsp.getHouseId());
        mav.addObject("hsp",hsp);
        return mav;
    }
    /*添加购买用户信息*/
@RequestMapping("purchaseAdd")
@ResponseBody
    public ResultVo purchaseAdd(Lease lease){
        ResultVo resultVo = agoLeaseService.purchaseAdd(lease);
        return resultVo;
    }
}
