package com.lf.background.sellhouse.service;

import com.lf.background.sellhouse.domain.SeachVo;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.foreground.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 代光磊 on 2019/6/11.
 */
public interface SellService {

    DataGrid<Sell> queryList(SeachVo sea, PageUtil page);

    String addSell(Sell sell);

    Sell selectByid(Integer sellId);

    String updateByid(Sell sell);

    String deleteSell(Integer seId);

    String exportExcel(HttpServletRequest request, HttpServletResponse response, SeachVo sea);

    List<Map<String,Integer>> selectHouseId();

    Sell selectAll(Integer sellId);
}
