package com.lf.background.house.hsell.service;

import com.lf.background.house.house.domain.House;
import com.lf.commons.ResultVo;

/**
 * Created by monst on 2019/6/11.
 */
public interface HsellServive {
    ResultVo insert(House house);

    ResultVo updateLeaseHouse(House house,Integer picId, String picPath);
}
