package com.lf.background.staff.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.staff.domain.Staff;
import com.lf.background.staff.domain.StaffSearchVo;
import com.lf.background.staff.mapper.StaffMapper;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.StrTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Zy on 2019/6/11.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    //条件查询
    @Override
    public DataGrid queryStaffList(PageUtil pageUtil, StaffSearchVo staffSearchVo) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //驼峰转
        String sort = StrTool.humpToLine2(pageUtil.getSort());
        List<Staff> staffList = staffMapper.queryStaffList(sort,pageUtil.getOrder(),staffSearchVo);
        DataGrid<Staff> dataGrid = new DataGrid<>();
            PageInfo<Staff> pageInfo = new PageInfo<>(staffList);
            dataGrid.setRows(staffList);
            dataGrid.setTotal(pageInfo.getTotal());
            return dataGrid;

        }
    //逻辑删除
    @Override
    public void deleteUpdate(Integer staffId) {
        staffMapper.deleteUpdate(staffId);
    }

    //修改回显
    @Override
    public Staff updateStaffById(Integer staffId) {
        return staffMapper.selectByPrimaryKey(staffId);
    }
    //修改
    @Override
    public String updateStaffId(Staff staff) {
        staffMapper.updateByPrimaryKeySelective(staff);
        return "OJBKupdate";
    }
    //添加员工信息
    @Override
    public void addStaff(Staff staff) {
        //状态码  1
        staff.setStaffStatus(1);
        //创建时间
        staff.setStaffCreateTime(new Date());
        //初始密码
        staff.setStaffPass("999");
        //以红色字体打印到控制台
        System.err.println(staff);
        staffMapper.insertSelective(staff);
    }


}
