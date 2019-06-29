package com.lf.foreground.house.service;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;

/**
 * Created by monst on 2019/6/19.
 */
public interface ForeHouseService {

    PageInfo<HouseShow> queryLeaseHouseList(Integer page, Integer rows);

    PageInfo<HouseShow> queryErHouseList(Integer page, Integer rows);

    PageInfo<HouseShow> queryNewHouseList(Integer page, Integer rows);

}
