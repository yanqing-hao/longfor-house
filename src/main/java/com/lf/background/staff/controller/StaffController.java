package com.lf.background.staff.controller;

import com.lf.background.staff.domain.Staff;
import com.lf.background.staff.domain.StaffSearchVo;
import com.lf.background.staff.service.StaffService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zy on 2019/6/11.
 */
@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    //查询
    @RequestMapping("queryStaffList")
    @ResponseBody
    public DataGrid queryStaffList(PageUtil pageUtil, StaffSearchVo staffSearchVo){
        return staffService.queryStaffList(pageUtil,staffSearchVo);
    }

    //修改回显
    @RequestMapping("updateStaffById")
    @ResponseBody
    public Staff updateStaffById(Integer staffId){

        return staffService.updateStaffById(staffId);
    }
    //修改ok
    @RequestMapping("updateStaffId")
    @ResponseBody
    public String updateStaffId(Staff staff){
        return staffService.updateStaffId(staff);
    }
    //去员工信息页修改
    @RequestMapping(value = "updateStaff")
    @ResponseBody
    public ModelAndView updateStaff(){
        ModelAndView mav = new ModelAndView("/background/staff/update");
        return mav;
    }
    //逻辑删除
    @RequestMapping("deleteUpdate")
    @ResponseBody
    public void deleteUpdate(Integer staffId) {
        staffService.deleteUpdate(staffId);
    }

    //添加员工信息
    @RequestMapping("addStaff")
    @ResponseBody
    public int addStaff(Staff staff){
        try{
            //CTRL + 鼠标点击
            staffService.addStaff(staff);
        }catch (Exception e){
            //失败
            return 0;
        }
        //成功
        return 1;
    }
    //tiao
    @RequestMapping("{url}")
    public String toUrl(@PathVariable("url") String url){
        return "/foreground/oldsellhouse/"+url;

    }
}
