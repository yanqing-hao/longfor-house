package com.lf.foreground.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 代光磊 on 2019/6/13.
 */
@Data
public class UserTemp {
    //用户主键
    private Integer userId;
    //用户姓名
    private String userName;
    //用户性别 1 男 2 女
    private Integer userSex;
    //用户密码
    private String userPass;
    //电子邮件
    private String userEmail;
    //出生日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirthday;
    //身份证
    private String userCard;
    //银行卡
    private String userBank;
    //联系方式
    private String userPhone;
    //备注
    private String userRemark;
    //创建时间
    private Date userCreateTime;
    //修改时间
    private Date userUpdateTime;
    //状态
    private Integer userStatus;
    //意向主键
    private Integer uiId;
    //图片路径
    private String userUrl;
    //房源主键 为了在前台展示 销售房源临时增加
    private Integer houseId;
    //房源主键 为了在前台展示 租赁房源临时增加
    private Integer houseId2;
}
