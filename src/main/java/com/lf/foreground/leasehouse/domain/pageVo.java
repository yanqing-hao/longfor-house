package com.lf.foreground.leasehouse.domain;

import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * Created by ASUS on 2019/6/18.
 */
@Data
public class pageVo {

    //当前页
    private Integer pageNum = 1;

    //每页条数
    private Integer pageSize = 5;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
