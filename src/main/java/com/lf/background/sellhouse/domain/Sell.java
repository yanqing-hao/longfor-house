package com.lf.background.sellhouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Sell {
    //编号
    private Integer sellId;
    //成交价
    private Double sellPrice;
    //税费
    private Double sellTaxation;
    //中介费
    private Double sellAgency;
    //定金
    private Double sellHandsel;
    //首付款
    private Double sellDown;
    //尾款
    private Double sellFinal;
    //按揭方式  1 公积金贷款 2 商业贷款 3组合贷款
    private Integer sellMortgage;
    //销售时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sellDate;
    //备注
    private String sellRemark;
    //创建时间
    private Date sellCreateTime;
    //修改时间
    private Date sellUpdateTime;
    //状态
    private Integer sellStatus;
    //房源外键
    private Integer houseId;
    //员工外键
    private Integer staffId;
    //用户主键
    private Integer userId;
    //用户名
    private String userName;
    //用户手机号
    private String userPhone;
    //用户银行卡号
    private String userBank;

}