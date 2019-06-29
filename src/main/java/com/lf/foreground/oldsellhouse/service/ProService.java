package com.lf.foreground.oldsellhouse.service;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.commons.PageUtil;
import com.lf.foreground.user.domain.User;

/**
 * Created by Zy on 2019/6/17.
 */
public interface ProService {

    //详情页
    HouseStaffPicture queryProId(Integer houseId);
    //展示页
    PageInfo<HouseShow> queryProList(Integer proSearch, Integer pageNum, Integer pageSize, PageUtil pageUtil);
    //添加
    void addPro(HouseStaffPicture houseStaffPicture, User user);
    //回显
    HouseStaffPicture addUpdate(Integer houseId);

}
