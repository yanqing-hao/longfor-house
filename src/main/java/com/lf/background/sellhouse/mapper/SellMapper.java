package com.lf.background.sellhouse.mapper;

import com.lf.background.sellhouse.domain.SeachVo;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.foreground.user.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SellMapper {
    int deleteByPrimaryKey(Integer sellId);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(Integer sellId);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell sell);

    List<Sell> queqyList(@Param("sea") SeachVo sea,
                         @Param("order") String order,
                         @Param("sort") String sort);

    List<Map<String,Integer>> selectHouseId();
}