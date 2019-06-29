package com.lf.background.leasehouse.mapper;

import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.leasehouse.domain.Lease;
import com.lf.background.leasehouse.domain.LeaseVo;
import com.lf.commons.PageUtil;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaseMapper {
    int deleteByPrimaryKey(Integer leaseId);

    int insert(Lease record);

    int insertSelective(Lease record);

    Lease selectByPrimaryKey(Integer leaseId);

    int updateByPrimaryKeySelective(Lease record);

    int updateByPrimaryKey(Lease record);
//查询  暂不使用
    int leasTotal(PageUtil page);

    //条件查询   +  普通查询
    List<Lease> leasList(LeaVo page);
//添加
    void leaseAdd(Lease lease);
//回显
    Lease leaaseEcho(Lease lease);
//修改
    void leaseUpdate(Lease lease);
//删除
    void leaseDelete(Integer leaseId);

    //通过id  回显对象
    LeaseVo leaaseEchoVo(Integer leaseId);
//文件导出
    List<Lease> queryLeaseList(@Param("lv") LeaVo lv,
                               @Param("sort") String sort,
                               @Param("order") String order);

    List<Map<String,Integer>> selectHouseId();
}