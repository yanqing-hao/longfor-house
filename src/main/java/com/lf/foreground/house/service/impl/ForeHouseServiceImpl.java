package com.lf.foreground.house.service.impl;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.commons.PageUtil;
import com.lf.foreground.house.service.ForeHouseService;
import com.lf.foreground.leasehouse.domain.OrderByVo;
import com.lf.foreground.leasehouse.service.AgoLeaseService;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import com.lf.foreground.newsellhouse.service.NewHouseService;
import com.lf.foreground.oldsellhouse.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by monst on 2019/6/19.
 */
@Service
@Transactional
public class ForeHouseServiceImpl implements ForeHouseService {

    //注入前台二手房查询
    @Autowired
    private ProService proService;
    //注入前台新房房查询
    @Autowired
    private NewHouseService newHouseService;
    //注入前台租赁房查询
    @Autowired
    private AgoLeaseService agoLeaseService;

    /**
     * 查询租赁房源列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfo<HouseShow> queryLeaseHouseList(Integer page, Integer rows) {
        return agoLeaseService.agoLeaseList(0,page,rows,new OrderByVo());
    }

    /**
     * 二手房源列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfo<HouseShow> queryErHouseList(Integer page, Integer rows) {
        return proService.queryProList(0,page,rows,new PageUtil());
    }

    /**
     * 新房房源列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfo<HouseShow> queryNewHouseList(Integer page, Integer rows) {
        return newHouseService.queryNewList(new NewSearch(),page,rows,new PageUtil());
    }
}
