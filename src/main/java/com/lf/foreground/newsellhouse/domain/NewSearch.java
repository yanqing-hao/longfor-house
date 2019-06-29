package com.lf.foreground.newsellhouse.domain;

import lombok.Data;

/**
 * Created by 代光磊 on 2019/6/16.
 */
@Data
public class NewSearch {

    //房屋价格 最低价
    private Double houseSellPriceB;
    //房屋价格 最高价
    private Double houseSellPriceE;
    //房屋面积 最小面积
    private Float staffAreaS;
    //房屋面积 最大面积
    private Float staffAreaB;
    //装修类型 1 清水 2精装 3 简装
    private Integer houseCase;
    //房型 1.一室一厅  2.一室一厅一卫 3.两室一厅一卫 4.三室一厅一卫    5.三室以上
    private Integer houseCount;
}
