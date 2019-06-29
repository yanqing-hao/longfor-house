package com.lf.background.leasehouse.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * Created by ASUS on 2019/6/13.
 */
//条件查询使用
    @Data
public class LeaVo {
    //房源编号
    private Integer houseId;
//开始时间
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;
//结束时间
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;
    //员工id
    private  Integer staffId;


}
