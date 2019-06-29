package com.lf.background.performance.mapper;

import com.lf.background.performance.domain.PerVo;
import com.lf.background.performance.domain.Performance;
import com.lf.commons.PageUtil;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PerformanceMapper {
    int deleteByPrimaryKey(Integer perId);

    int insert(Performance record);

    int insertSelective(Performance record);

    Performance selectByPrimaryKey(Integer perId);

    int updateByPrimaryKeySelective(Performance record);

    int updateByPrimaryKey(Performance record);
//查询销售业绩
    List<Performance> performanceList(PerVo page);
//导出数据表
    List<Performance> queryPerformanceList(@Param("pv") PerVo pv,
                                           @Param("sort") String sort,
                                           @Param("order") String order);
//查询x租赁业绩
    List<Performance> performanceList2(PerVo vo);
}