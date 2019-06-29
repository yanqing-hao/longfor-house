package com.lf.foreground.newsellhouse.service;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.commons.PageUtil;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import com.lf.foreground.user.domain.User;

import java.util.List;

/**
 * Created by 代光磊 on 2019/6/16.
 */
public interface NewHouseService {
    PageInfo<HouseShow> queryNewList(NewSearch sea, Integer pageNum, Integer pageSize, PageUtil newPage);

    List<HouseStaffPicture> selectNewAll(Integer houseId);

    House buyHouse(Integer houseId);

    void newHouseDown(User user, House house);

}
