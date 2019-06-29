package com.lf.background.performance.domain;

import lombok.Data;

@Data
public class Performance {
    //业绩主键
    private Integer perId;
//销售单数
    private Integer perSellCount;
//总销售额
    private Double perSellPrice;
//销售中介费总额
    private Double perSellAgency;
//租赁单数
    private Integer perLeaseCount;
   // 租赁中介费总额
    private Double perLeasePrice;
//员工主键
    private Integer staffId;
    //员工姓名
    private String staffName;


}