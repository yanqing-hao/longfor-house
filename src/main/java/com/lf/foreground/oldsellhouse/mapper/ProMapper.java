package com.lf.foreground.oldsellhouse.mapper;

import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.commons.PageUtil;
import com.lf.foreground.oldsellhouse.domain.ProSearchVo;
import com.lf.foreground.user.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Zy on 2019/6/17.
 */
public interface ProMapper {

    //详情页
    HouseStaffPicture queryProId(Integer houseId);

    //展示页
    List<HouseShow> queryProList(@Param("proSearchVo") ProSearchVo proSearchVo,
                                 @Param("sort") String sort,
                                 @Param("order") String order);

    //添加销售信息
    void addPro(HouseStaffPicture houseStaffPicture, User user);

    //购买回显
    HouseStaffPicture addUpdate(Integer houseId);
}
