package com.lf.background.staff.service;

import com.lf.background.staff.domain.Staff;
import com.lf.background.staff.domain.StaffSearchVo;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;

/**
 * Created by Zy on 2019/6/11.
 */
public interface StaffService {
    DataGrid queryStaffList(PageUtil pageUtil, StaffSearchVo staffSearchVo);

    void deleteUpdate(Integer staffId);

    Staff updateStaffById(Integer staffId);

    String updateStaffId(Staff staff);

    //ctrl +t+鼠标单击
    void addStaff(Staff staff);
}
