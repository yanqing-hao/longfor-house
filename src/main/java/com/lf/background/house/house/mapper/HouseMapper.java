package com.lf.background.house.house.mapper;

import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseSearch;
import com.lf.background.house.house.domain.HouseStaffPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer houseId);

    int insert(House record);

    int insertSelective(House record);

    HouseStaffPicture selectByPrimaryKey(Integer houseId);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<HouseStaffPicture> getHouseList(@Param("sort") String sort,
                                         @Param("order") String order,
                                         @Param("houseSearch") HouseSearch houseSearch);

    void updateHouseStatus(Integer houseId);

    /*租赁房源列表*/
    List<HouseStaffPicture> queryLeaseHouseList(Integer houseMethod);

    /*销售房源列表*/
    List<HouseStaffPicture> querySellHouseList(@Param("houseMethod") Integer houseMethod,
                                               @Param("houseRtype")  Integer houseRtype);
    /*修改房屋状态（是否出租 与 销售）*/
    void updateHouseSellLease(Integer houseId);

}