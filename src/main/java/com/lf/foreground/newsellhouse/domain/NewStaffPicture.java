package com.lf.foreground.newsellhouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lf.background.picture.domain.Picture;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by 代光磊 on 2019/6/19.
 */
public class NewStaffPicture {
    private Integer houseId;
    //业务方式       1.出租  2.销售
    private Integer houseMethod;

    private String houseTitle;

    private Float staffArea;

    private String houseName;
    //面积
    private String houseAddr;
    //室厅卫数 下拉  1.一室一厅  2.一室一厅一卫 3.两室一厅一卫 4.三室一厅一卫    5.三室以上 6.单间
    private Integer houseCount;
    //所在楼层
    private Integer houseFloor;
    //总楼层
    private Integer houseSumFloor;
    //装修情况                    1.清水  2.精装  3.简装
    private Integer houseCase;
    //建筑类型                    1.普通住宅  2.宿舍  3.公寓  4.别墅
    private Integer houseBuild;
    //房屋朝向
    private String houseDirection;
    //行政区市
    private String houseRegion;
    //房屋类型---销售         1.新房  2.二手房
    private Integer houseRtype;
    //销售价格-----销售
    private Double houseSellPrice;
    //开放商------销售
    private String houseDevelopers;
    //产权--------销售
    private String houseEquity;
    //房屋配置--------租赁    1.电视  2.洗衣机  3.空调  4.沙发  5.衣柜 6.天然气  7.宽带  8.热水器  9.消毒柜  10.冰箱  11.油烟机
    private String houseConfigure;
    //出租方式------租赁      1.押一付三   2.押一付一  3.押二付三
    private Integer houseLeaseType;
    //出租价格--------租赁
    private Double houseLeasePrice;
    //房屋状态    0.未支付/出租     1.已支付/出租
    private Integer houseStatus;
    //备注
    private String houseRemark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date houseCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date houseUpdateTime;

    private Integer houseHstatus;

    private String houseAbout;

    //private Staff staff;

    //员工编号
    private Integer staffId;
    //员工姓名
    private String staffName;
    //private Integer picId;  //图片id
    //private String picPath; //图片路径

    private String  pictureList;
}
