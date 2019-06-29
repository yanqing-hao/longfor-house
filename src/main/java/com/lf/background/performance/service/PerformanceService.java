package com.lf.background.performance.service;

import com.lf.background.leasehouse.domain.LeaVo;
import com.lf.background.performance.domain.PerVo;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ASUS on 2019/6/13.
 */
public interface PerformanceService {
    //查询
    DataGrid performanceList(PageUtil page, PerVo vo);

    DataGrid performanceList2(PageUtil page, PerVo vo);
}
