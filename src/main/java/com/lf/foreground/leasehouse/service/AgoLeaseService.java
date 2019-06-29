package com.lf.foreground.leasehouse.service;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.commons.ResultVo;
import com.lf.foreground.leasehouse.domain.OrderByVo;

import java.util.List;

/**
 * Created by ASUS on 2019/6/17.
 */
public interface AgoLeaseService {
    /*查询*/
    PageInfo<HouseShow> agoLeaseList(Integer state, Integer pageNum, Integer pageSize, OrderByVo orvo);
    /*回显*/
    HouseStaffPicture agoParticulars(Integer houseBuild);
    /*添加*/
    ResultVo purchaseAdd(Lease lease);

}
