package com.lf.background.leasehouse.service;

import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.background.leasehouse.domain.LeaseVo;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/6/11.
 */
public interface LeaseService {
 //查询
   DataGrid leasList(PageUtil page,LeaVo vo);
    //ctrl +t+鼠标单击
 //添加
    void leaseAdd(Lease lease);

    Lease leaaseEcho(Lease lease);
//修改
    void leaseUpdate(Lease lease);

//逻辑删除
    void leaseDelete(Lease lease);
    //通过id  回显对象
    LeaseVo leaaseEchoVo(Integer leaseId);
//导出数据
    void exportStuListExcel(HttpServletRequest request, HttpServletResponse response, LeaVo lv);

    List<Map<String,Integer>> selectLeaseHouseId();

}
