package com.lf.background.leasehouse.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class LeaseVo {

   private Integer leaseId;
//租金
   private Double leaseRent;
//租客姓名
   private String userName;
//租客电话
   private String userPhone;
//中介费
   private Double leaseAgency;
//开始时间
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date startTime;
//结束时间
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date endTime;
//扫描合同
   private String leaseCompact;
//押金
   private Double leaseCashPledge;
//备注
   private String leaseRemark;
//创建时间
   private Date leaseCreateTime;
//修改时间
   private Date leaseUpdateTime;
//状态码 0隐藏 1显示
    private Integer leaseStatus;
//员工主键
    private Integer staffId;
//房源主键
    private Integer houseId;
//用户主键
    private Integer userId;
//用户银行卡号
    private String userBank;


    //修改时间
    public String getLeaseUpdateTimes(){
        if(leaseUpdateTime != null){
            return new SimpleDateFormat("yyyy-MM-dd").format(leaseUpdateTime);
        }
        return "";
    }




    //开始时间
    public String getStartTimes(){
        if(startTime != null){
            return new SimpleDateFormat("yyyy-MM-dd").format(startTime);
        }
        return "";
    }

    //结束时间
    public String getEndTimes(){
        if(endTime != null){
            return new SimpleDateFormat("yyyy-MM-dd").format(endTime);
        }
        return "";
    }


}