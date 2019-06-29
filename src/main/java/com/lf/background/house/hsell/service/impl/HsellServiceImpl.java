package com.lf.background.house.hsell.service.impl;

import com.lf.background.house.house.domain.House;
import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.house.hsell.service.HsellServive;
import com.lf.background.picture.domain.Picture;
import com.lf.background.picture.mapper.PictureMapper;
import com.lf.background.staff.domain.Staff;
import com.lf.commons.RedisUtil;
import com.lf.commons.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by monst on 2019/6/11.
 */
@Service
@Transactional
public class HsellServiceImpl implements HsellServive {

    //注入房源mapper
    @Autowired
    private HouseMapper houseMapper;

    //注入图片mapper
    @Autowired
    private PictureMapper pictureMapper;

    //注入redisUtil
    @Autowired
    private RedisUtil redisUtil;

    //注入SolrTemplate
    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 发布销售房源
     * @param house
     * @return
     */
    @Override
    public ResultVo insert(House house) {
        ResultVo resultVo = new ResultVo(501,"房源发布失败");
        house.setHouseMethod(2);   //业务是销售
        house.setHouseCreateTime(new Date());  //创建时间
        house.setHouseHstatus(1);    //状态码  显示
        house.setHouseStatus(0);     //房屋状态  未销售
        Staff staff = (Staff) redisUtil.get("staff");
        house.setStaffId(staff.getStaffId());   //员工id
        houseMapper.insertSelective(house);   // 添加房源信息
        //将house对象放入solr
        house.setId(house.getHouseId());  //用houseId给solr的id赋值
        solrTemplate.saveBean("house_core",house);
        solrTemplate.commit("house_core");
        Integer houseId = house.getHouseId();  //获取该房源id
        if(house.getPicPath()!=null&&!house.getPicPath().equals("")) {  //判断是否有房源图片
            String[] split = house.getPicPath().split(",");
            //遍历添加图片
            for(int i=0;i<split.length;i++){
                //创建图片对象
                Picture picture = new Picture(split[i],i,new Date(),1,houseId);
                pictureMapper.insertSelective(picture);  //新增图片
                //将picture对象放入solr
                solrTemplate.saveBean("picture_core",picture);
                solrTemplate.commit("picture_core");
            }
        }else{
            //创建改房源编号的空路径图片对象
            Picture picture = new Picture("",0,new Date(),1,houseId);
            pictureMapper.insertSelective(picture);  //新增图片
        }
        resultVo.setCode(200);
        resultVo.setInfo("房源发布成功");
        return resultVo;
    }

    /**
     * 修改销售房源
     * @param house
     * @param picPath
     * @return
     */
    @Override
    public ResultVo updateLeaseHouse(House house,Integer picId, String picPath) {
        ResultVo resultVo = new ResultVo(501,"修改房源失败");
        house.setHouseHstatus(1);     //状态码  显示
        house.setHouseUpdateTime(new Date());  //修改时间
        houseMapper.updateByPrimaryKeySelective(house);   // 修改房源信息
        //修改solr中的house对象
        house.setId(house.getHouseId());  //用houseId给solr的id赋值
        solrTemplate.saveBean("house_core",house);
        solrTemplate.commit("house_core");
        resultVo.setCode(200);
        resultVo.setInfo("修改房源成功");
        return resultVo;
    }
}
