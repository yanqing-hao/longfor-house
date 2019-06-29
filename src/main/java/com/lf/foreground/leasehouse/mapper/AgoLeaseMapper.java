package com.lf.foreground.leasehouse.mapper;

import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.foreground.leasehouse.domain.pirceVo;

import java.util.List;

/**
 * Created by ASUS on 2019/6/17.
 */
public interface AgoLeaseMapper {
    //查询
   List<HouseShow> agoLeaseList(pirceVo vo);
    //回显
    HouseStaffPicture agoParticulars(Integer houseBuild);
    //添加
    void purchaseAdd(Lease lease);
}
