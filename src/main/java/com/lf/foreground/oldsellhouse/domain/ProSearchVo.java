package com.lf.foreground.oldsellhouse.domain;

import lombok.Data;

/**
 * Created by Zy on 2019/6/17.
 */
@Data
public class ProSearchVo {
   //房源区域
    private String proAddr;

    //价格低
    private Integer proPriceD;
    //高
    private Integer proPriceG;

    //小面积
    private Integer proAreaD;
    //大面积
    private Integer proAreaG;

    //房型
    private Integer proFloor;


    //添加有参无参构造
    public ProSearchVo(Integer proPriceD, Integer proPriceG, Integer proAreaD, Integer proAreaG, Integer proFloor) {
     this.proPriceD = proPriceD;
     this.proPriceG = proPriceG;
     this.proAreaD = proAreaD;
     this.proAreaG = proAreaG;
     this.proFloor = proFloor;
    }

    public ProSearchVo() {
       }
}

