package com.lf.background.house.house.controller;

import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.HouseSearch;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.house.house.service.HouseService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by monst on 2019/6/11.
 * 房源管理
 */
@Api(description = "房源管理模块")
@RestController
@RequestMapping("house")
@Slf4j
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 房源列表展示
     * @param page
     * @param houseSearch
     * @return
     */
    @RequestMapping("houseList")
    @ApiOperation(value="房源列表展示",notes = "房源列表展示")
    public DataGrid<HouseStaffPicture> houseList(PageUtil page, HouseSearch houseSearch){
        log.info("条件查询对象houseSearch={}",houseSearch);
        log.info("分页工具类page={}",page);
        return houseService.getHouseList(page,houseSearch);
    }

    /**
     * 根据id删除信息
     * @param houseId
     * @return
     */
    @GetMapping("delete/{houseId}")
    @ApiOperation(value="根据ID删除房源信息",notes = "根据ID删除房源信息")
    public ResultVo deleteHouse(@PathVariable("houseId") Integer houseId){
        log.info("接收idhouseId={}",houseId);
        return houseService.deleteHouse(houseId);
    }

    /**
     * 根据id回显信息
     * @param houseId
     * @return
     */
    @GetMapping("reShow/{houseId}")
    @ApiOperation(value="根据ID回显房源信息",notes = "根据ID回显房源信息")
    public HouseStaffPicture getHouseById(@PathVariable("houseId") Integer houseId){
        log.info("接收idhouseId={}",houseId);
        return houseService.getHouseById(houseId);
    }


    /**
     * 修改房源状态  0.未出租/销售  1.已出租/销售
     * @return
     */
    @GetMapping("updateHouseSellLease")
    @ApiOperation(value="修改房源状态",notes = "修改房源状态")
    public void updateHouseSellLease(Integer houseId){
       houseService.updateHouseSellLease(houseId);
    }

}
