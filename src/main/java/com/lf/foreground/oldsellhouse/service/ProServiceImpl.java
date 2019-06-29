package com.lf.foreground.oldsellhouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.domain.HouseShow;
import com.lf.background.house.house.domain.HouseStaffPicture;
import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.background.sellhouse.service.SellService;
import com.lf.commons.PageUtil;
import com.lf.commons.StrTool;
import com.lf.foreground.oldsellhouse.domain.ProSearchVo;
import com.lf.foreground.oldsellhouse.mapper.ProMapper;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.service.UserService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Zy on 2019/6/17.
 */
@Service
public class ProServiceImpl implements ProService{
    @Autowired
    private ProMapper proMapper;
    //注入SellService
    @Autowired
    private SellService sellService;
    //注入用户接口
    @Autowired
    private UserService userService;
    //注入房源接口
    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private SolrTemplate solrTemplate;


    //定义一个List 为空
    static List<ProSearchVo> proListTiao = null;

    static {
        //实例化List
        proListTiao = new ArrayList();


        proListTiao.add(new ProSearchVo(null,null,null,null,null));
        proListTiao.add(new ProSearchVo(500,1000,null,null,null));
        proListTiao.add(new ProSearchVo(1001,2000,null,null,null));
        proListTiao.add(new ProSearchVo(2001,3000,null,null,null));
        proListTiao.add(new ProSearchVo(3001,4000,null,null,null));
        proListTiao.add(new ProSearchVo(null,null,null,null,null));
        proListTiao.add(new ProSearchVo(null,null,10,null,null));
        proListTiao.add(new ProSearchVo(null,null,20,40,null));
        proListTiao.add(new ProSearchVo(null,null,41,60,null));
        proListTiao.add(new ProSearchVo(null,null,61,80,null));
        proListTiao.add(new ProSearchVo(null,null,100,null,null));
        proListTiao.add(new ProSearchVo(null,null,null,null,null));
        proListTiao.add(new ProSearchVo(null,null,null,null,1));
        proListTiao.add(new ProSearchVo(null,null,null,null,2));
        proListTiao.add(new ProSearchVo(null,null,null,null,3));
        proListTiao.add(new ProSearchVo(null,null,null,null,4));


    }

    //查询所有 ，分页条件
/*    @Override
    public List<HouseStaffPicture> queryProList(ProSearchVo proSearchVo) {
        PageHelper.startPage(page,rows);
        List list = proMapper.queryProList(new ProSearchVo(proListTiao.get()));
        PageInfo<HouseStaffPicture> pageInfo = new PageInfo<HouseStaffPicture>(list);
        return list;
    }*/

/*    @Override
    public List<HouseStaffPicture> queryProList(Integer proSearch) {

        //如果状态为空的话
        if (proSearch == null){
            //默认选择“不限”
            proSearch = 0;
        }
        List list = proMapper.queryProList(new ProSearchVo(proListTiao.get(proSearch).getProPriceD(),proListTiao.get(proSearch).getProPriceG(),
                proListTiao.get(proSearch).getProAreaD(),proListTiao.get(proSearch).getProAreaG(),
                proListTiao.get(proSearch).getProFloor()));
        return list;
    }*/

    //单挑查询
    @Override
    public HouseStaffPicture queryProId(Integer houseId) {
        return proMapper.queryProId(houseId);
    }
    //主展示页
    @Override
    public PageInfo<HouseShow> queryProList(Integer proSearch, Integer pageNum, Integer pageSize, PageUtil pageUtil) {
        List<HouseShow> houseShowList = new ArrayList<>();
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("houseTitle").contains("");
        query.addCriteria(criteria);
        //设置高亮的域
        HighlightOptions highlightOptions = new HighlightOptions().addField("houseTitle");
        //高亮前缀
        highlightOptions.setSimplePrefix("<font style =color:yellow;font-size:30px>");
        //高亮后缀
        highlightOptions.setSimplePostfix("</font>");
        //高亮选项
        query.setHighlightOptions(highlightOptions);


        HighlightPage<HouseShow> houseStaffPicturePage = solrTemplate.queryForHighlightPage("house_core",query,HouseShow.class);

        //设置高亮结果
        List<HighlightEntry<HouseShow>> highlightEntries = houseStaffPicturePage.getHighlighted();
        //循环高亮入口集合
        for (HighlightEntry<HouseShow> h:highlightEntries){
            //获取员实体类
            HouseShow houseShow = h.getEntity();
            if (h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
                //设置高亮的结果
                houseShow.setHouseTitle(h.getHighlights().get(0).getSnipplets().get(0));
                houseShowList.add(houseShow);
            }
        }


        //分页工具类
        PageHelper.startPage(pageNum,pageSize);
        //将驼峰转下划线
        pageUtil.setSort(StrTool.humpToLine2(pageUtil.getSort()));

        //List<HouseStaffPicture> list1 = proMapper.queryProList(proSearch,pageUtil.getSort(),pageUtil.getOrder());


//        if (proSearch == null){
//            //默认选择“不限”
//            proSearch = 0;
//        }
//        List<HouseShow> list = proMapper.queryProList(new ProSearchVo(proListTiao.get(proSearch).getProPriceD(),proListTiao.get(proSearch).getProPriceG(),
//                proListTiao.get(proSearch).getProAreaD(),proListTiao.get(proSearch).getProAreaG(),
//                proListTiao.get(proSearch).getProFloor()),pageUtil.getSort(),pageUtil.getOrder());
        //page对象
        PageInfo<HouseShow> pageInfo = new PageInfo<HouseShow>(houseShowList);
        return pageInfo;
    }
    //添加销售二手房
    @Override
    public void addPro(HouseStaffPicture houseStaffPicture,User user) {
        HouseStaffPicture houseStaffPicture1 = addUpdate(houseStaffPicture.getHouseId());
        Sell sell = new Sell();
        //成交价
        sell.setSellPrice(houseStaffPicture1.getHouseSellPrice());
        //税费 销售价的2%
        sell.setSellTaxation(houseStaffPicture1.getHouseSellPrice()*0.02);
        //中介费 销售价的5%
        sell.setSellAgency(houseStaffPicture1.getHouseSellPrice()*0.05);
        //定金 销售价的2%
        sell.setSellHandsel(houseStaffPicture1.getHouseSellPrice()*0.02);
        //首付款 销售价10%
        sell.setSellDown(houseStaffPicture1.getHouseSellPrice()*0.01);
        //用户名
        sell.setUserName(user.getUserName());
        //银行卡号
        sell.setUserBank(user.getUserBank());
        //手机号
        sell.setUserPhone(user.getUserPhone());
        //尾款 成交价-首付-定价
        sell.setSellFinal(houseStaffPicture1.getHouseSellPrice()-sell.getSellDown()-sell.getSellHandsel());
        //按揭方式 1 公积金 2商业贷款 3组合贷款
         sell.setSellMortgage(1);
        //销售时间 为数据添加到数据库的时间
        sell.setSellDate(new Date());
        //员工主键  发布人的id
        sell.setStaffId(15);
        //添加房源编号
        sell.setHouseId(houseStaffPicture.getHouseId());

        //添加销售记录  将房源状态变成已销售
        sellService.addSell(sell);
        //添加用户信息
        userService.addUser(user);

    }


    //购买回显
    @Override
    public HouseStaffPicture addUpdate(Integer houseId) {
        return proMapper.addUpdate(houseId);
    }

}