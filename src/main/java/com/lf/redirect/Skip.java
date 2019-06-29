package com.lf.redirect;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by monst on 2019/6/11.
 */
@RestController
@RequestMapping("skip")
@Api(description = "用于templates跳转")
public class Skip {

/*****************************************后台************************************************/
    /**
     * main
     * 去龙湖房地产管理页面
     * @return
     */
    @GetMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView("background/main");
        return mav;
    }

    /**
     * 去发布租赁房源页面
     * @return
     */
    @GetMapping("toInsertLeaseHouse")
    public ModelAndView toInsertLeaseHouse(){
        ModelAndView mav = new ModelAndView("background/house/leaseHouse-insert");
        return mav;
    }

    /**
     * 去发布销售房源页面
     * @return
     */
    @GetMapping("toInsertSellHouse")
    public ModelAndView toInsertSellHouse(){
        ModelAndView mav = new ModelAndView("background/house/sellHouse-insert");
        return mav;
    }

    /**
     * 去房源展示页面
     * @return
     */
    @GetMapping("toHouseList")
    public ModelAndView toHouseList(){
        ModelAndView mav = new ModelAndView("background/house/house-list");
        return mav;
    }

    /**
     * 去房源详情页面
     * @return
     */
    @GetMapping("toGetHouse")
    public ModelAndView toGetHouse(){
        ModelAndView mav = new ModelAndView("background/house/house-details");
        return mav;
    }

    /**
     * 租房添加页面
     * @return
     */
    @GetMapping("toLeaseInsert")
    public ModelAndView toLeaseInsert(){
        ModelAndView mav = new ModelAndView("background/leaseHouse/lease_add");
        return mav;
    }

    /**
     * 租房展示页面
     * @return
     */
    @GetMapping("toLeaseList")
    public ModelAndView toLeaseList(){
        ModelAndView mav = new ModelAndView("background/leaseHouse/lease_list");
        return mav;
    }

    /**
     * 售房添加页面
     * @return
     */
    @GetMapping("toSellInsert")
    public ModelAndView toSellInsert(){
        ModelAndView mav = new ModelAndView("background/sellHouse/sellHouse-insert");
        return mav;
    }
    /**
     * 售房添加页面2
     * @return
     */
    @GetMapping("toSellInsertTow")
    public ModelAndView toSellInsertTow(){
        ModelAndView mav = new ModelAndView("background/sellHouse/sellHouse-insert2");
        return mav;
    }

    /**
     * 售房展示页面
     * @return
     */
    @GetMapping("toSellList")
    public ModelAndView toSellList(){
        ModelAndView mav = new ModelAndView("background/sellHouse/sellHouse-list");
        return mav;
    }

    /**
     * 销售信息修改页面
     * @return
     */
    @GetMapping("toSellUPdate")
    public ModelAndView toSellUPdate(){
        ModelAndView mav = new ModelAndView("background/sellHouse/sellHouse-update");
        return mav;
    }
    /**
     * 用户添加页面
     * @return
     */
    @GetMapping("toUserAdd")
    public ModelAndView toUserInsert(){
        ModelAndView mav = new ModelAndView("foreground/user/user-insert");
        return mav;
    }
    /**
     * 去另一个用户添加页面
     * @return
     */
    @GetMapping("toUserInsertTow")
    public ModelAndView toUserInsertTow(){
        ModelAndView mav = new ModelAndView("foreground/user/user-insert2");
        return mav;
    }
    /**
     * 用户展示页面
     * @return
     */
    @GetMapping("toUserList")
    public ModelAndView toUserList(){
        ModelAndView mav = new ModelAndView("foreground/user/user-list");
        return mav;
    }
    /**
     * 用户修改页面
     * @return
     */
    @GetMapping("toUserUpdate")
    public ModelAndView toUserUpdate(){
        ModelAndView mav = new ModelAndView("foreground/user/user-update");
        return mav;
    }
    /**
     * 去员工管理平台
     */
    @GetMapping("toStaffMain")
    public ModelAndView toStaffMain(){
        ModelAndView mav = new ModelAndView("/background/staff/main");
        return mav;
    }
    /**
     * 去添加员工信息
     */
    @GetMapping("toAddStaff")
    public ModelAndView toAddStaff(){
        ModelAndView mav = new ModelAndView("/background/staff/employeeAdd");
        return mav;
    }

    /**
     * 去员工管理平台登陆页面
     */
    @GetMapping("toStaffLogin")
    public ModelAndView toStaffLogin(){
        ModelAndView mav = new ModelAndView("/background/staffLogin");
        return mav;
    }
    //去员工信息页
    @GetMapping(value = "staffList")
    public ModelAndView staffList(){
        ModelAndView mav = new ModelAndView("/background/staff/list");
        return mav;
    }

    /**
     * 获取租赁表单页
     * @return
     */
    @GetMapping(value = "getLeaseHouseForm")
    public ModelAndView getLeaseHouseForm(){
        ModelAndView mav = new ModelAndView("/background/house/insertForm/lease-house-form-insert");
        return mav;
    }

    /**
     * 获取销售表单页
     * @return
     */
    @GetMapping(value = "getSellHouseForm")
    public ModelAndView getSellHouseForm(){
        ModelAndView mav = new ModelAndView("/background/house/insertForm/sell-house-form-insert");
        return mav;
    }

    /**
     * 去员工销售业绩查看
     */
    @GetMapping(value = "toStaffLists")
    public ModelAndView toStaffLists(){
        ModelAndView mav = new ModelAndView("/background/performance/performance_list");
        return mav;
    }

    /**
     * 去员工租赁业绩查看
     */
    @GetMapping(value = "toStaffLists2")
    public ModelAndView toStaffLists2(){
        ModelAndView mav = new ModelAndView("/background/performance/performancelease_list");
        return mav;
    }

/*****************************************前台************************************************/

    /**
     * 去前台首页
     */
    @GetMapping(value = "toForeHome")
    public ModelAndView toForeHome(){
        ModelAndView mav = new ModelAndView("/foreground/home/main");
        return mav;
    }

    /**
     * 去前台租赁页
     */
    @GetMapping(value = "toForeLeaseHouse")
    public ModelAndView toForeLeaseHouse(){
        ModelAndView mav = new ModelAndView("/foreground/leasehouse/leasehouse-mian");
        return mav;
    }

    /**
     * 去前台二手房页
     */
    @GetMapping(value = "toForeOldSellHouse")
    public ModelAndView toForeOldSellHouse(){
        ModelAndView mav = new ModelAndView("/foreground/oldsellhouse/oldsellhouse-mian");
        return mav;
    }

    /**
     * 去前台新房页
     */
    @GetMapping(value = "toForeNewSellHouse")
    public ModelAndView toForeNewSellHouse(){
        ModelAndView mav = new ModelAndView("/foreground/newsellhouse/newsellhouse-mian");
        return mav;
    }

    /**
     * 个人中心
     */
    @GetMapping(value = "toPersonal")
    public ModelAndView toPersonal(){
        ModelAndView mav = new ModelAndView("/foreground/personal/personal-main");
        return mav;
    }

    /**
     * 去前台登录
     */
    @GetMapping(value = "toUserLogin")
    public ModelAndView toUserLogin(){
        ModelAndView mav = new ModelAndView("/foreground/registlogin/user-login");
        return mav;
    }

    /**
     * 去前台注册
     */
    @GetMapping(value = "toUserRegist")
    public ModelAndView toUserRegist(){
        ModelAndView mav = new ModelAndView("/foreground/registlogin/user-regist");
        return mav;
    }
}
