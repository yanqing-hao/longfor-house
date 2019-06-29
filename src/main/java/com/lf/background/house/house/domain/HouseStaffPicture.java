package com.lf.background.house.house.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lf.background.picture.domain.Picture;
import com.lf.background.staff.domain.Staff;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class HouseStaffPicture implements Serializable {
    @Field
    private Integer houseId;
    //业务方式       1.出租  2.销售
    @Field
    private Integer houseMethod;
    @Field
    private String houseTitle;
    @Field
    private Float staffArea;
    @Field
    private String houseName;
    //面积
    @Field
    private String houseAddr;
    //室厅卫数 下拉  1.一室一厅  2.一室一厅一卫 3.两室一厅一卫 4.三室一厅一卫    5.三室以上 6.单间
    @Field
    private Integer houseCount;
    //所在楼层
    @Field
    private Integer houseFloor;
    //总楼层
    @Field
    private Integer houseSumFloor;
    //装修情况                    1.清水  2.精装  3.简装
    @Field
    private Integer houseCase;
    //建筑类型                    1.普通住宅  2.宿舍  3.公寓  4.别墅
    @Field
    private Integer houseBuild;
    //房屋朝向
    @Field
    private String houseDirection;
    //行政区市
    @Field
    private String houseRegion;
    //房屋类型---销售         1.新房  2.二手房
    @Field
    private Integer houseRtype;
    //销售价格-----销售
    @Field
    private Double houseSellPrice;
    //开放商------销售
    @Field
    private String houseDevelopers;
    //产权--------销售
    @Field
    private String houseEquity;
    //房屋配置--------租赁    1.电视  2.洗衣机  3.空调  4.沙发  5.衣柜 6.天然气  7.宽带  8.热水器  9.消毒柜  10.冰箱  11.油烟机
    @Field
    private String houseConfigure;
    //出租方式------租赁      1.押一付三   2.押一付一  3.押二付三
    @Field
    private Integer houseLeaseType;
    //出租价格--------租赁
    @Field
    private Double houseLeasePrice;
    //房屋状态    0.未支付/出租     1.已支付/出租
    @Field
    private Integer houseStatus;
    //备注
    @Field
    private String houseRemark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field
    private Date houseCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field
    private Date houseUpdateTime;
    @Field
    private Integer houseHstatus;
    @Field
    private String houseAbout;

    //private Staff staff;

    //员工编号
    @Field
    private Integer staffId;
    //员工姓名
    @Field
    private String staffName;
    //private Integer picId;  //图片id
    //private String picPath; //图片路径
    @Field
    private List<Picture> pictureList;
    //用于solr给id赋值
    @Field
    private Integer id;

}