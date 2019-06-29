package com.lf.background.house.house.domain;

import com.lf.background.staff.domain.Staff;
import lombok.Data;

@Data
public class HouseSearch {

    private String createTimeB; //发布开始时间

    private String createTimeE; //发布结束时间

    private String staffName;      //发布者-->姓名

    private Integer houseStatus;  //房屋状态  0.为出租/销售   1.已出租/销售

    private Integer houseMethod;  //业务方式    1.租赁   2.销售

    private Integer staffId;  //员工编号 （发布者）

}