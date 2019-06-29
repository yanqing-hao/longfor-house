package com.lf.background.sellhouse.controller;

import com.lf.background.sellhouse.domain.SeachVo;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.background.sellhouse.service.SellService;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.foreground.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 代光磊 on 2019/6/11.
 */
@Controller
@Api(tags = "销售模块")  //对类的说明
@RequestMapping("sell")
public class SellController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SellController.class);

    @Autowired
    private SellService sellService;

    /**
     * 条件查询和分页
     * @param sea
     * @param page
     * @return
     */
    @ApiOperation(value = "条件和分页查询")  //此注解是为了说明方法的作用和功能
    @ResponseBody
    @RequestMapping("queryList")
    public DataGrid<Sell> queryList(SeachVo sea, PageUtil page){
        LOGGER.info("条件查询和分页 sea = {} page = {}",sea,page);
        return sellService.queryList(sea, page);
    }

    /**
     * 添加销售信息
     * @param sell
     */
    @ApiOperation("添加销售信息")
    @ResponseBody
    @RequestMapping("addSell")
    public String addSell(Sell sell){
        LOGGER.info("添加销售信息 sell={}",sell);
       return sellService.addSell(sell);
    }

    /**
     * 根据ID查询
     * @return
     */
    @ApiOperation("根据ID查询")
    @ResponseBody
    @RequestMapping("selectByid")
    public Sell selectByid(Integer sellId){
        LOGGER.info("根据ID查询 sellId={}",sellId);
        return sellService.selectByid(sellId);
    }


    /**
     * 修改方法
     * @param sell
     */
    @ApiOperation("根据ID修改")
    @ResponseBody
    @RequestMapping("updateByid")
    public String updateByid(Sell sell){
        LOGGER.info("根据ID修改 seid={}",sell.getSellId());
        return sellService.updateByid(sell);
    }

    /**
     * 删除方法
     * @param sellId
     */
    @ResponseBody
    @RequestMapping(value = "deleteSell",produces="application/json; charset=UTF-8")
    @ApiOperation("删除方法")
    public String deleteSell(Integer sellId){
        LOGGER.info("逻辑删除 seid={}",sellId);
        return sellService.deleteSell(sellId);
    }

    /**
     * excel 导出
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("excel 导出")
    @RequestMapping("exportExcel")
    @ResponseBody
    public String exportExcel(HttpServletRequest request, HttpServletResponse response,SeachVo sea) {
        LOGGER.info("request {} response {} sea {}",request,response,sea);
        return sellService.exportExcel(request,response,sea);
    }

    /**
     * 查询房源id放到添加页面的下拉列表中
     * @return
     */
    @ApiOperation("查询房源id")
    @ResponseBody
    @RequestMapping("selectHouseId")
    public List<Map<String,Integer>> selectHouseId() {
        List<Map<String,Integer>> selectHouseId = sellService.selectHouseId();
        LOGGER.info("id {}",selectHouseId);
        return selectHouseId;
    }

    @ApiOperation("查询详细信息")
    @ResponseBody
    @RequestMapping("selectAll")
    public ModelAndView selectAll(Integer sellId){
        LOGGER.info("sellId {}",sellId);
        ModelAndView mav = new ModelAndView("/background/sellHouse/sellHouse-listAll");
        Sell sell = sellService.selectByid(sellId);
        LOGGER.info("查询出来的sell{} ",sell);
        mav.addObject("sell",sell);
        return mav;
    }

}
