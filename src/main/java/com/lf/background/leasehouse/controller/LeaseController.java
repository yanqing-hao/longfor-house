package com.lf.background.leasehouse.controller;

import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.background.leasehouse.domain.LeaseVo;
import com.lf.background.leasehouse.service.LeaseService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/6/11.
 */
@Controller
@RequestMapping("leasehouser")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaseController.class);
    /*查询*/
    @RequestMapping("leaseList")
    @ResponseBody
    // 添加VO  用来接收前台传过来的条件查询的值
    public DataGrid leaseList(PageUtil page , LeaVo vo){
      /*  easyui里面的DataGrid组件   使用easyui*/
        DataGrid dg=  leaseService.leasList(page , vo);
        return dg;
    }
    /*添加*/
    @RequestMapping("leaseAdd")
    @ResponseBody
    public int leaseAdd(Lease lease){
        try{
            //CTRL + 鼠标点击
            leaseService.leaseAdd(lease);
        }catch (Exception e){
            System.out.println(e);
            //失败
            return 0;
        }
        //成功
        return 1;
    }


    //回显
    @RequestMapping("leaaseEcho")
    public ModelAndView leaaseEcho(LeaseVo vo){
        ModelAndView mo = new ModelAndView();
        //通过id  查询对象
        vo = leaseService.leaaseEchoVo(vo.getLeaseId());
        //返回前台的页面数据
        mo.addObject("vo",vo);
        //返回前台的页面路径
        mo.setViewName("/background/leaseHouse/lease_echo");
        return mo;
    }


    /*修改*/
    @RequestMapping("leaseUpdate")
    @ResponseBody
    public int leaseUpdate(Lease lease){
        leaseService.leaseUpdate(lease);
        return 0;
        }
    /*删除*/
    @RequestMapping("leaseDelete")
    @ResponseBody
    public void  leaseDelete(Lease lease){
        leaseService.leaseDelete(lease);
    }



    //详情  调用回显接口
    @RequestMapping("leaaseParticulars")
    public ModelAndView leaaseParticulars(LeaseVo vo){
        ModelAndView mo = new ModelAndView();
        //通过id  查询对象
        vo = leaseService.leaaseEchoVo(vo.getLeaseId());
        //返回前台的页面数据
        mo.addObject("vo",vo);
        //返回前台的页面路径
        mo.setViewName("/background/leaseHouse/lease_pariculars");
        return mo;
    }
    //导出数据
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,
                            HttpServletResponse response, LeaVo lv) {
        leaseService.exportStuListExcel(request,response,lv);
    }


    /**
     * 查询房源id放到添加页面的下拉列表中
     * @return
     */
    @ApiOperation("查询房源id")
    @ResponseBody
    @RequestMapping("selectLeaseHouseId")
    public List<Map<String,Integer>> selectLeaseHouseId() {
        List<Map<String,Integer>> selectHouseId = leaseService.selectLeaseHouseId();
        LOGGER.info("id {}",selectHouseId);
        return selectHouseId;
    }

}
