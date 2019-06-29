package com.lf.background.performance.controller;

import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.performance.domain.PerVo;
import com.lf.background.performance.service.PerformanceService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ASUS on 2019/6/13.
 */
@Controller
@RequestMapping("performanceController")
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    /*查询销售业绩*/
    @RequestMapping("performanceList")
    @ResponseBody
    public DataGrid performanceList(PageUtil page, PerVo vo){
       DataGrid dg= performanceService.performanceList(page,vo);
        return dg;
    }

    /*查询租赁业绩*/
    @RequestMapping("performanceList2")
    @ResponseBody
    public DataGrid performanceList2(PageUtil page, PerVo vo){
       DataGrid dg= performanceService.performanceList2(page,vo);
        return dg;
    }


}
