package com.lf.foreground.leasehouse.domain;

import lombok.Data;

/**
 * Created by ASUS on 2019/6/17.
 */
@Data
public class pirceVo {

    //最小金额
    private Integer pirB;

    //最大金额
    private Integer pirE;

    //最小面积
    private Integer staffAreaB;
    //最大面积
    private Integer staffAreaE;
    ////室厅卫数 下拉  1.单间  1.一室一厅  2.一室一厅一卫 3.两室一厅一卫 4.三室一厅一卫    5.三室以上
    private  Integer houseCount;

    //升序  倒序
    private String orderby;

    //字段
    private String orderbyDate;



    public pirceVo() {
    }
//有参构造

    public pirceVo(Integer pirB, Integer pirE, Integer staffAreaB, Integer staffAreaE, Integer houseCount) {
        this.pirB = pirB;
        this.pirE = pirE;
        this.staffAreaB = staffAreaB;
        this.staffAreaE = staffAreaE;
        this.houseCount = houseCount;
    }

    public pirceVo(Integer pirB, Integer pirE, Integer staffAreaB, Integer staffAreaE, Integer houseCount, String orderby, String orderbyDate) {
        this.pirB = pirB;
        this.pirE = pirE;
        this.staffAreaB = staffAreaB;
        this.staffAreaE = staffAreaE;
        this.houseCount = houseCount;
        this.orderby = orderby;
        this.orderbyDate = orderbyDate;
    }
}
