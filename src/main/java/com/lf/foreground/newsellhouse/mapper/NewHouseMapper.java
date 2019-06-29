package com.lf.foreground.newsellhouse.mapper;

import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 代光磊 on 2019/6/16.
 */
public interface NewHouseMapper {

    List<HouseShow> queryList(@Param("sea") NewSearch sea, @Param("sort") String sort, @Param("order") String order);

    List<HouseStaffPicture> selectNewAll(Integer houseId);

    House buyHouse(Integer houseId);
}
