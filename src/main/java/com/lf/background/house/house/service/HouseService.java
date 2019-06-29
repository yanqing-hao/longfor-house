package com.lf.background.house.house.service;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseSearch;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.ResultVo;

import java.util.List;

/**
 * Created by monst on 2019/6/11.
 */
public interface HouseService {
    DataGrid<HouseStaffPicture> getHouseList(PageUtil page, HouseSearch houseSearch);

    ResultVo deleteHouse(Integer houseId);

    HouseStaffPicture getHouseById(Integer houseId);

    void updateHouseSellLease(Integer houseId);

}
