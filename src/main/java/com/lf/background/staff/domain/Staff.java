package com.lf.background.staff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Staff {
    //员工主键
    private Integer staffId;
    //员工姓名
    private String staffName;
    //员工性别
    private Integer staffSex;
    //联系方式
    private String staffPhone;
    //邮件
    private String staffEmail;
    //员工密码
    private String staffPass;
    //身份证号
    private String staffCard;
    //出生日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date staffBirthday;
    //家庭住址
    private String staffAddress;
    //所属分店
    private Integer staffSubbranch;
    //加入时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date staffDate;
    //职位     1.经理  2.一般员工  3.管理员',
    private Integer staffPost;
    //权限    1.一般员工   2.管理员',
    private Integer staffShiro;
    //创建时间
    private Date staffCreateTime;
    //修改时间
    private Date staffUpdateTime;
    //状态码   0.隐藏    1.显示',
    private Integer staffStatus;


}