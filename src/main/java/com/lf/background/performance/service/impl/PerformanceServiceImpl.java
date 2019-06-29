package com.lf.background.performance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.leasehouse.util.PoiExprotUtils;
import com.lf.background.performance.domain.PerVo;
import com.lf.background.performance.domain.Performance;
import com.lf.background.performance.mapper.PerformanceMapper;
import com.lf.background.performance.service.PerformanceService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.StrTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ASUS on 2019/6/13.
 */
@Service
@Slf4j
public class PerformanceServiceImpl implements PerformanceService{
    @Autowired
    private PerformanceMapper performanceMapper;
    /*查询销售业绩*/
    @Override
    public DataGrid performanceList(PageUtil page, PerVo vo) {
            DataGrid dg=new DataGrid();
            //准备分页
            //jdk中自带的工具类
            PageHelper.startPage(page.getPage(),page.getRows());
            //排序字段驼峰转换 StrTool是驼峰转换工具类 humpToLine2驼峰转换方法
            page.setSort(StrTool.humpToLine2(page.getSort()));
            //查询 //shift+alt+L
           List<Performance> list= performanceMapper.performanceList(vo);
           //实现分页
        //jdk中自带的方法  PageInfo
        PageInfo<Performance> pageInfo=new PageInfo<Performance>(list);
        dg.setTotal(pageInfo.getTotal());
        dg.setRows(list);
        System.out.println(list.toString());
        return dg;

    }

    /*查询租赁业绩*/
    @Override
    public DataGrid performanceList2(PageUtil page, PerVo vo) {
        DataGrid dg=new DataGrid();
        //准备分页
        //jdk中自带的工具类
        PageHelper.startPage(page.getPage(),page.getRows());
        //排序字段驼峰转换 StrTool是驼峰转换工具类 humpToLine2驼峰转换方法
        page.setSort(StrTool.humpToLine2(page.getSort()));
        //查询 //shift+alt+L
        List<Performance> list= performanceMapper.performanceList2(vo);
        //实现分页
        //jdk中自带的方法  PageInfo
        PageInfo<Performance> pageInfo=new PageInfo<Performance>(list);
        dg.setTotal(pageInfo.getTotal());
        dg.setRows(list);
        System.out.println(list.toString());
        return dg;
    }

}
