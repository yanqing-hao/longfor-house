package com.lf.foreground.leasehouse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;

import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.house.house.service.HouseService;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.commons.ResultVo;
import com.lf.foreground.leasehouse.domain.OrderByVo;
import com.lf.foreground.leasehouse.domain.pirceVo;
import com.lf.foreground.leasehouse.mapper.AgoLeaseMapper;
import com.lf.foreground.leasehouse.service.AgoLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/6/17.
 */
@Service
public class AgoLeasehServiceImpl implements AgoLeaseService{
    @Autowired
    private AgoLeaseMapper agoLeaseMapper;

    //注入houseMapper
    @Autowired
    private HouseMapper houseMapper;

    //注入houseService
    @Autowired
    private HouseService houseService;

    static List<pirceVo> list = null;
/*价格条件*/
/*思路这是以数组的形式展示的，根据下标
domain 中使用的是有参构造
多个条件查询都是以下标 连续查询的
*/
    static {
        list = new ArrayList();

        list.add(new pirceVo(null,null,null,null,null));
        list.add(new pirceVo(500,1500,null,null,null));
        list.add(new pirceVo(1600,2500,null,null,null));
        list.add(new pirceVo(2600,3900,null,null,null));
        list.add(new pirceVo(4000,null,null,null,null));
        list.add(new pirceVo(null,null,null,null,null));
        list.add(new pirceVo(null,null,10,20,null));
        list.add(new pirceVo(null,null,21,40,null));
        list.add(new pirceVo(null,null,41,60,null));
        list.add(new pirceVo(null,null,61,80,null));
        list.add(new pirceVo(null,null,100,null,null));
        list.add(new pirceVo(null,null,null,null,null));
        list.add(new pirceVo(null,null,null,null,1));
        list.add(new pirceVo(null,null,null,null,2));
        list.add(new pirceVo(null,null,null,null,3));
        list.add(new pirceVo(null,null,null,null,4));
        list.add(new pirceVo(null,null,null,null,5));
        list.add(new pirceVo(null,null,null,null,6));

    }

    @Override
    public PageInfo<HouseShow> agoLeaseList(Integer state, Integer pageNum, Integer pageSize, OrderByVo orvo) {
        if(state == null){
            state = 0;
        }
        //    Mybatis-config.xml                     当前页        每页条数
        PageHelper.startPage(pageNum,pageSize);

        /*查询*/
        List<HouseShow> list1=  agoLeaseMapper.agoLeaseList(new pirceVo(list.get(state).getPirB(),list.get(state).getPirE(),list.get(state).getStaffAreaB(),list.get(state).getStaffAreaE(),list.get(state).getHouseCount(),orvo.getOrderby(),orvo.getOrderbyDate()));
        //使用pageInfo 进行分页
        PageInfo<HouseShow> pageInfo = new PageInfo<HouseShow>(list1);
        return pageInfo;
    }
/*回显*/
    @Override
    public HouseStaffPicture agoParticulars(Integer houseBuild) {
        return   houseMapper.selectByPrimaryKey(houseBuild);
    }
/*添加功能  房屋购买模块*/
    @Override
    public ResultVo purchaseAdd(Lease lease) {
        ResultVo resultVo = new ResultVo(500,"购买失败");
        //更改房源状态为已租赁
        houseService.updateHouseSellLease(lease.getHouseId());
        //添加租赁信息
        agoLeaseMapper.purchaseAdd(lease);
        resultVo.setCode(200);
        resultVo.setInfo("购买成功");
        return resultVo;
    }


}
