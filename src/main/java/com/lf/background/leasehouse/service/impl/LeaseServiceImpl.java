package com.lf.background.leasehouse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.background.leasehouse.domain.LeaseVo;
import com.lf.background.leasehouse.mapper.LeaseMapper;
import com.lf.background.leasehouse.service.LeaseService;
import com.lf.background.leasehouse.util.PoiExprotUtils;
import com.lf.background.staff.domain.Staff;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.RedisUtil;
import com.lf.commons.StrTool;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/6/11.
 * ctrl+alt+左右方向键
 */
@Service
public class LeaseServiceImpl implements LeaseService{
    @Autowired
    private LeaseMapper leaseMapper;
    //注入UserService
    @Autowired
    private UserService userService;
    //注入redis
    @Autowired
    private RedisUtil redisUtil;
    //注入houseMapper
    @Autowired
    private HouseMapper houseMapper;
    /*条件查询*/
   @Override
    public DataGrid leasList(PageUtil page,LeaVo vo) {
//使用easuui DataGrid 列表展示
        DataGrid dg=new DataGrid();
        //准备分页//page.getPage(),page.getRows()
        PageHelper.startPage(page.getPage(),page.getRows());
        //排序字段驼峰转换//humpToLine2还有humpToLine这个作用是什么
        page.setSort(StrTool.humpToLine2(page.getSort()));
       //获取当前登录的员工信息
       Staff staff = (Staff) redisUtil.get("staff");
       //租赁表中用户id
       vo.setStaffId(staff.getStaffId());
       //查询
        List<Lease> list= leaseMapper.leasList(vo);
        //实现分页
        PageInfo<Lease> pageInfo=new PageInfo<Lease>(list);
        dg.setTotal(pageInfo.getTotal());
        dg.setRows(list);
        return dg;
    }
/*添加*/
    @Override
    //ctrl +t+鼠标单击
    public void leaseAdd(Lease lease) {
        /* 创建当前时间 */
        lease.setLeaseCreateTime(new Date());
        //获取当前登录的员工信息
        Staff staff = (Staff) redisUtil.get("staff");
        //给租赁表赋值员工id
        lease.setStaffId(staff.getStaffId());
        //以红色字体打印到控制台
        System.err.println(lease);
        //状态默认展示
        lease.setLeaseStatus(1);
        //房源的状态变为已销售
        houseMapper.updateHouseSellLease(lease.getHouseId());
        leaseMapper.leaseAdd(lease);

    }
/*回显*/
    @Override
    public Lease leaaseEcho(Lease lease) {
        return leaseMapper.leaaseEcho(lease);
    }
/*修改*/
    @Override
    public void leaseUpdate(Lease lease) {
        //添加当前更改表的时间
        lease.setLeaseUpdateTime(new Date());
        leaseMapper.leaseUpdate(lease);
    }
/*删除*/
    @Override
    public void leaseDelete(Lease lease) {
        leaseMapper.leaseDelete(lease.getLeaseId());
    }

    //通过id  回显对象
    @Override
    public LeaseVo leaaseEchoVo(Integer leaseId) {
        return leaseMapper.leaaseEchoVo(leaseId);
    }
//导出表格
    @Override
    public void exportStuListExcel(HttpServletRequest request, HttpServletResponse response, LeaVo lv) {
        //对条件查询的结束时间格式化
        if(lv != null
                && lv.getEndTime() != null
                && lv.getEndTime().length() > 0) {
            lv.setEndTime(lv.getEndTime()+" 23:59:59");
        }

        List<Lease> leasesList = leaseMapper.queryLeaseList(lv,null,null);

        //调用封装的导出excel 工具包
        PoiExprotUtils poiExprotUtils = new PoiExprotUtils();
        poiExprotUtils.exportStuListExcel(request,response,leasesList,"租赁信息列表");
    }

    /**
     * 查询房源id放到添加页面的下拉列表中
     * @return
     */
    @Override
    public List<Map<String, Integer>> selectLeaseHouseId() {
        List<Map<String,Integer>> list = leaseMapper.selectHouseId();
        return list;
    }

}
