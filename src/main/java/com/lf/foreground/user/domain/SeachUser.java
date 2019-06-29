package com.lf.foreground.user.domain;

import lombok.Data;

/**
 * Created by 代光磊 on 2019/6/13.
 */
@Data
public class SeachUser {
    private Integer userHouseId;  //房源编号

    private Integer userUserId;  //用户编号

    private String userUserName;  //用户姓名
}
