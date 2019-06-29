package com.lf.background.picture.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;
@Data
public class Picture implements Serializable {
    @Field
    private Integer picId;
    @Field
    private String picPath;
    @Field
    private Integer picType;
    @Field
    private Date picCreateTime;
    @Field
    private Date picUpdateTime;
    @Field
    private Integer picStatus;
    @Field
    private Integer houseId;

    public Picture() {
    }

    //用于新增
    public Picture(String picPath,Integer picType, Date picCreateTime, Integer picStatus, Integer houseId) {
        this.picPath = picPath;
        this.picType = picType;
        this.picCreateTime = picCreateTime;
        this.picStatus = picStatus;
        this.houseId = houseId;
    }

    //用于修改
    public Picture(Integer picId, String picPath, Date picUpdateTime, Integer houseId) {
        this.picId = picId;
        this.picPath = picPath;
        this.picUpdateTime = picUpdateTime;
        this.houseId = houseId;
    }

}
