package com.lf.foreground.newsellhouse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.background.sellhouse.service.SellService;
import com.lf.commons.PageUtil;
import com.lf.commons.StrTool;
import com.lf.foreground.newsellhouse.domain.NewSearch;
import com.lf.foreground.newsellhouse.mapper.NewHouseMapper;
import com.lf.foreground.newsellhouse.service.NewHouseService;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 代光磊 on 2019/6/16.
 */
@Service
@Transactional(readOnly = false)
public class NewHouseServiceImpl implements NewHouseService {

    @Autowired
    private NewHouseMapper newHouseMapper;

    //注入SellService
    @Autowired
    private SellService sellService;
    //注入用户接口
    @Autowired
    private UserService userService;
    //注入solr工具类
    @Autowired
    private SolrTemplate solrTemplate;


    /**
     * 条件 分页排序 查询列表
     *
     * @return
     */
    @Override
    public PageInfo<HouseShow> queryNewList(NewSearch sea, Integer pageNum, Integer pageSize, PageUtil newPage) {

        //查询所有
        Query query = new SimpleQuery("*:*");
        //HighlightQuery query = new SimpleHighlightQuery();

        //条件 装修类型
        if (sea != null) {
            Criteria criteria = new Criteria("houseName").is("*");
            if (sea.getHouseCount() != null) {
                criteria = criteria.and("houseCount").is(sea.getHouseCount());
            }
            //条件 销售价格
            if (sea.getHouseSellPriceB() != null) {
                criteria = criteria.and("houseSellPrice").between(sea.getHouseSellPriceB(), sea.getHouseSellPriceE());
            }
            if (sea.getStaffAreaS() != null) {
                criteria = criteria.and("staffArea").between(sea.getStaffAreaS(), sea.getStaffAreaB());
            }
            query.addCriteria(criteria);
        }
        //排序
        if (newPage.getSort().equals("")){
            Sort sort = new Sort(Sort.Direction.DESC,"houseId");
            query.addSort(sort);
        }
        if (newPage.getSort()!=null&&!newPage.equals("")){
            if (newPage.getOrder().equals("asc")){
                Sort sort = new Sort(Sort.Direction.ASC,"houseSellPrice");
                query.addSort(sort);
            }
            if (newPage.getOrder().equals("desc")){
                Sort sort = new Sort(Sort.Direction.DESC,"houseSellPrice");
                query.addSort(sort);
            }
        }
        Page<HouseShow> house_core = solrTemplate.query("house_core", query, HouseShow.class);
        List<HouseShow> content = house_core.getContent();
        System.out.println(content);

        /*/设置高亮的域                                                  //高亮字段:房屋面积
        HighlightOptions highlightOptions = new HighlightOptions().addField("houseTitle");
        //高亮前缀
        highlightOptions.setSimplePrefix("<font color='red'>");
        //设置高亮后缀
        highlightOptions.setSimplePostfix("</font>");
        //设置高亮选项
        query.setHighlightOptions(highlightOptions);
        HighlightPage<HouseShow> houPage = solrTemplate.queryForHighlightPage("house_core", query, HouseShow.class);
        //设置高亮的结果
        List<HighlightEntry<HouseShow>> highlighted = houPage.getHighlighted();
        for (HighlightEntry<HouseShow> hle:highlighted){  //循环高亮集合入口
            HouseShow houseShow = hle.getEntity();  //获取原实体类
            if(hle.getHighlights().size()>0 && hle.getHighlights().get(0).getSnipplets().size()>0){
                //设置高亮结果
                houseShow.setHouseTitle(hle.getHighlights().get(0).getSnipplets().get(0));
            }
        }
        System.out.println("总记录数"+houPage.getTotalElements());
        List<HouseShow> content = houPage.getContent();*/

        //分页工具类
        PageHelper.startPage(pageNum, pageSize);
        //将驼峰转下划线
       /* newPage.setSort(StrTool.humpToLine2(newPage.getSort()));
        List<HouseShow> list = newHouseMapper.queryList(sea,newPage.getSort(),newPage.getOrder());*/

        //page对象
        PageInfo<HouseShow> pageInfo = new PageInfo<>(content);
        System.out.println("实现类的" + pageInfo);
        return pageInfo;
    }

    /**
     * 详情查询
     *
     * @param houseId
     * @return
     */
    @Override
    public List<HouseStaffPicture> selectNewAll(Integer houseId) {
        return newHouseMapper.selectNewAll(houseId);
    }


    /**
     * 前台点击购买时查询房源详情
     *
     * @param houseId
     * @return
     */
    @Override
    public House buyHouse(Integer houseId) {
        return newHouseMapper.buyHouse(houseId);
    }

    /**
     * 确认购房 将用户信息添加到用户表
     * 增加新房销售记录
     *
     * @param user
     * @param house
     * @return
     */
    @Override
    public void newHouseDown(User user, House house) {
        House house1 = buyHouse(house.getHouseId());
        Sell sell = new Sell();
        //成交价
        sell.setSellPrice(house1.getHouseSellPrice());
        //税费 销售价的2%
        sell.setSellTaxation(house1.getHouseSellPrice() * 0.02);
        //中介费 销售价的5%
        sell.setSellAgency(house1.getHouseSellPrice() * 0.01);
        //定金 销售价的2%
        sell.setSellHandsel(house1.getHouseSellPrice() * 0.02);
        //首付款 销售价10%
        sell.setSellDown(house1.getHouseSellPrice() * 0.1);
        //用户名
        sell.setUserName(user.getUserName());
        //银行卡号
        sell.setUserBank(user.getUserBank());
        //手机号
        sell.setUserPhone(user.getUserPhone());
        //尾款 成交价-首付-定价
        sell.setSellFinal(house1.getHouseSellPrice() - sell.getSellDown() - sell.getSellHandsel());
        //按揭方式 1 公积金 2商业贷款 3组合贷款
        sell.setSellMortgage(1);
        //销售时间 为数据添加到数据库的时间
        sell.setSellDate(new Date());
        //员工主键  发布人的id
        sell.setStaffId(house1.getStaffId());
        //添加房源编号
        sell.setHouseId(house.getHouseId());

        //邮箱
        String[] split = user.getUserEmail().split(",");
        String s = split[1];
        if (s.equals("1")) {
            user.setUserEmail(split[0] + "@163.com");
        } else if (s.equals("2")) {
            user.setUserEmail(split[0] + "@qq.com");
        } else {
            user.setUserEmail(split[0] + "@sina.com");
        }
        //添加销售记录  将房源状态变成已销售
        sellService.addSell(sell);
        //添加用户信息
        userService.addUser(user);

    }
}

