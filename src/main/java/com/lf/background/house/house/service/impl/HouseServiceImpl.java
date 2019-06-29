package com.lf.background.house.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseSearch;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.house.house.service.HouseService;
import com.lf.background.staff.domain.Staff;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.RedisUtil;
import com.lf.commons.ResultVo;
import com.lf.commons.StrTool;
import com.lf.foreground.leasehouse.domain.OrderByVo;
import com.lf.foreground.leasehouse.service.AgoLeaseService;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import com.lf.foreground.newsellhouse.service.NewHouseService;
import com.lf.foreground.oldsellhouse.domain.ProSearchVo;
import com.lf.foreground.oldsellhouse.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by monst on 2019/6/11.
 */
@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    //注入redis
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 房源列表展示
     * @param page
     * @param houseSearch
     * @return
     */
    @Override
    public DataGrid<HouseStaffPicture> getHouseList(PageUtil page, HouseSearch houseSearch) {
        DataGrid<HouseStaffPicture> dg = new DataGrid<HouseStaffPicture>();
        //准备分页了
        PageHelper.startPage(page.getPage(),page.getRows());
        //排序字段驼峰转化
        page.setSort(StrTool.humpToLine2(page.getSort()));
        //结束时间格式化  (拼接上结束时分秒)
        if(houseSearch!=null&&houseSearch.getCreateTimeE()!=null&&!houseSearch.getCreateTimeE().equals("")){
            houseSearch.setCreateTimeE(houseSearch.getCreateTimeE()+" 23:59:59");
        }
        Staff staff = (Staff) redisUtil.get("staff"); //获取缓存
        if(staff!=null){
            houseSearch.setStaffId(staff.getStaffId());  //获取发布者Id
        }
        //大查询
        List<HouseStaffPicture> list = houseMapper.getHouseList(page.getSort(),page.getOrder(),houseSearch);
        //实现分页
        PageInfo<HouseStaffPicture> pageInfo = new PageInfo<HouseStaffPicture>(list);
        dg.setTotal(pageInfo.getTotal());
        dg.setRows(list);
        return dg;
    }

    /**
     * 根据id删除房源信息  （逻辑删）
     *  有待优化（先查）
     * @param houseId
     * @return
     */
    @Override
    public ResultVo deleteHouse(Integer houseId) {
        ResultVo resultVo = new ResultVo(501,"删除失败");
        houseMapper.updateHouseStatus(houseId);
        resultVo.setCode(200);
        resultVo.setInfo("删除成功了");
        return resultVo;
    }

    /**
     * 根据id查询
     * @param houseId
     * @return
     */
    @Override
    public HouseStaffPicture getHouseById(Integer houseId) {
        return houseMapper.selectByPrimaryKey(houseId);
    }

    /**
     * 修改房源状态
     */
    @Override
    public void updateHouseSellLease(Integer houseId) {
        houseMapper.updateHouseSellLease(houseId);
    }
}
