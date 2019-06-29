package com.lf.background.sellhouse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.background.house.house.mapper.HouseMapper;
import com.lf.background.sellhouse.domain.SeachVo;
import com.lf.background.sellhouse.domain.Sell;
import com.lf.background.sellhouse.encodingUtil.FileUtils;
import com.lf.background.sellhouse.mapper.SellMapper;
import com.lf.background.sellhouse.service.SellService;
import com.lf.background.staff.domain.Staff;
import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.RedisUtil;
import com.lf.commons.StrTool;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by 代光磊 on 2019/6/11.
 */
@Service
@Transactional(readOnly = false)
public class SellServiceImpl implements SellService {

    @Autowired
    private SellMapper sellMapper;
    //注入UserService
    @Autowired
    private UserService userService;
    //注入redis
    @Autowired
    private RedisUtil redisUtil;
    //注入houseMapper
    @Autowired
    private HouseMapper houseMapper;
    /*
    * 分页查询和条件查询
    * */
    @Override
    @Transactional(readOnly = true)
    public DataGrid<Sell> queryList(SeachVo sea, PageUtil page) {
        //分页工具类
        PageHelper.startPage(page.getPage(),page.getRows());
        //排序  驼峰转下划线
        String sort = StrTool.humpToLine2(page.getSort());
        Staff staff = (Staff) redisUtil.get("staff");
        sea.setStaffId(staff.getStaffId());
        List<Sell> list = sellMapper.queqyList(sea,page.getOrder(),sort);
        //PageInfo对象
        PageInfo<Sell> pageInfo = new PageInfo<>(list);
        DataGrid<Sell> dg = new DataGrid<>();
        dg.setTotal(pageInfo.getTotal());      //返回给easyUI的数据总条数
        dg.setRows(list);  //返回给easyUI的数据的集合
        return dg;
    }

    /**
     * 添加销售信息
     * @param sell
     */
    @Override
    public String addSell(Sell sell) {
        //从缓存中获取员工id
        Staff staff = (Staff) redisUtil.get("staff");
        sell.setStaffId(staff.getStaffId());
      /*  //获取当前登录的用户信息
        User user = (User) redisUtil.get("user");
        if(user!=null){  //user不为空证明 前台已登录
            //给销售表赋值用户id
            sell.setUserId(user.getUserId());
        }else{  //user为空 前台没登录  说明是在后台操作  要先添加用户，再完成销售
            User user1 = new User(sell.getUserName(),sell.getUserPhone());
            userService.addUser(user1);
            //给销售表赋值用户id
            sell.setUserId(user1.getUserId());
        }*/
        houseMapper.updateHouseSellLease(sell.getHouseId());
        sellMapper.insert(sell); //销售表添加成功后   房源的状态变为已销售
        return "添加数据成功";
    }

    /**
     * 根据ID查询
     * @param sellId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Sell selectByid(Integer sellId) {
        return sellMapper.selectByPrimaryKey(sellId);
    }

    /**
     * 根据id修改方法
     * @param sell
     */
    @Override
    public String updateByid(Sell sell) {
        sellMapper.updateByPrimaryKey(sell);
        return "修改信息成功";
    }

    /**
     * 逻辑删除方法
     * @param seId
     */
    @Override
    public String deleteSell(Integer seId) {
        sellMapper.deleteByPrimaryKey(seId);
        return "删除成功!!!";
    }

    /**
     * excel 导出
     * @param request
     * @param response
     * @param sea
     * @return
     */
    @Override
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, SeachVo sea) {
        // 創建 Excel 對象
        XSSFWorkbook workbook = new XSSFWorkbook();
        //創建表
        XSSFSheet sheet = workbook.createSheet();
        //起表名
        workbook.setSheetName(0, "销售信息");
        //字体样式
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("楷体");
        font.setColor(new XSSFColor(Color.blue));
        font.setFontHeight(20);
        //设置样式
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //竖直居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //背景颜色
        style.setFillForegroundColor(IndexedColors.PINK.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //边框
        style.setBorderTop(BorderStyle.HAIR);
        style.setBorderBottom(BorderStyle.HAIR);
        style.setBorderLeft(BorderStyle.HAIR);
        style.setBorderRight(BorderStyle.HAIR);
        //列宽
        sheet.setColumnWidth((short) 0, (short) 12 * 265);
        sheet.setColumnWidth((short) 1, (short) 12 * 265);
        sheet.setColumnWidth((short) 2, (short) 12 * 265);
        sheet.setColumnWidth((short) 3, (short) 12 * 265);
        sheet.setColumnWidth((short) 4, (short) 12 * 265);
        sheet.setColumnWidth((short) 5, (short) 12 * 265);
        sheet.setColumnWidth((short) 6, (short) 12 * 265);
        //創建第一行
        XSSFRow firstRow = sheet.createRow(0);
//        XSSFRow secendRow = sheet.createRow(1);
        //創建第一列
        XSSFCell titleCell1 = firstRow.createCell(0);
//        XSSFCell titleCell2 = firstRow.createCell(1);
//        XSSFCell titleCell3 = firstRow.createCell(2);
//        XSSFCell titleCell4 = firstRow.createCell(3);
//        XSSFCell titleCell5 = secendRow.createCell(0);
//        XSSFCell titleCell6 = secendRow.createCell(1);
//        XSSFCell titleCell7 = secendRow.createCell(2);
//        XSSFCell titleCell8 = secendRow.createCell(3);
        //合並單元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 6);
        sheet.addMergedRegion(cellRangeAddress);
        //第一列內容
        titleCell1.setCellValue("销售信息记录");
        //给单元格添加样式
        titleCell1.setCellStyle(style);

        //设置样式-表头
        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setBold(true);
        font1.setFontName("楷体");
        font1.setFontHeight(12);
        cellStyle1.setFont(font1);
        //背景颜色
        cellStyle1.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //竖直居中
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        //边框
        cellStyle1.setBorderTop(BorderStyle.THIN);
        cellStyle1.setBorderBottom(BorderStyle.THIN);
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        cellStyle1.setBorderRight(BorderStyle.THIN);
        //創建表頭
        XSSFRow headCell = sheet.createRow(2);
        for (int i = 0; i <7; i++) {
            XSSFCell cell = headCell.createCell(i);
            cell.setCellStyle(cellStyle1);
            if (i == 0) cell.setCellValue("房源编号");
            if (i == 1) cell.setCellValue("员工编号");
            if (i == 2) cell.setCellValue("用户名");
            if (i == 3) cell.setCellValue("用户手机号");
            if (i == 4) cell.setCellValue("成交价");
            if (i == 5) cell.setCellValue("销售时间");
            if (i == 6) cell.setCellValue("尾款");
        }
        //设置样式2
        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setBold(true);
        font2.setFontName("宋体");
        font2.setFontHeight(10);
        cellStyle2.setFont(font2);
        //横向靠左
        cellStyle2.setAlignment(HorizontalAlignment.LEFT);
        //背景颜色
        cellStyle2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //边框
        cellStyle2.setBorderTop(BorderStyle.THIN);
        cellStyle2.setBorderBottom(BorderStyle.THIN);
        cellStyle2.setBorderLeft(BorderStyle.THIN);
        cellStyle2.setBorderRight(BorderStyle.THIN);

        //设置样式3
        XSSFCellStyle cellStyle3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setBold(true);
        font3.setFontName("宋体");
        font3.setFontHeight(10);
        cellStyle3.setFont(font3);
        //横向靠左
        cellStyle3.setAlignment(HorizontalAlignment.LEFT);
        //边框
        cellStyle3.setBorderTop(BorderStyle.HAIR);
        cellStyle3.setBorderBottom(BorderStyle.HAIR);
        cellStyle3.setBorderLeft(BorderStyle.HAIR);
        cellStyle3.setBorderRight(BorderStyle.HAIR);

        //格式化时间
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        //調用按名稱查詢列表方法
        List<Sell> list = sellMapper.queqyList(sea, null, null);
        System.out.println(list);
        //遍歷集合并创建数据行
        for (Sell sell : list) {
            //獲取最後一行的行號
            int lastRowNum = sheet.getLastRowNum();
            //创建数据行
            XSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            int rowNum = dataRow.getRowNum();
            // 房源编号
            XSSFCell hidCell = dataRow.createCell(0);
            if (sell.getHouseId()!=null){
                hidCell.setCellValue(sell.getHouseId());
            }
            // 员工编号
            XSSFCell staIdCell = dataRow.createCell(1);
            if (sell.getStaffId()!=null){
                staIdCell.setCellValue(sell.getStaffId());
            }

            //用户名
            XSSFCell userNameCell = dataRow.createCell(2);
            if (sell.getUserName() != null) {
                userNameCell.setCellValue(sell.getUserName());
            }
            //用户手机号
            XSSFCell userPhoneCell = dataRow.createCell(3);
            if (sell.getUserPhone() != null) {
                userPhoneCell.setCellValue(sell.getUserPhone());
            }
            //成交价
            XSSFCell sellPrice = dataRow.createCell(4);
            if (sell.getSellPrice() != null) {
                sellPrice.setCellValue(sell.getSellPrice());
            }
            //销售时间
            XSSFCell sellDate = dataRow.createCell(5);
            if (sell.getSellDate() != null) {
                String format = sim.format(sell.getSellDate());
                sellDate.setCellValue(format);
            }
            //尾款

            XSSFCell sellFinal = dataRow.createCell(6);
            if (sell.getSellFinal() != null) {
                sellFinal.setCellValue(sell.getSellFinal());
            }
            //数据行选择样式
            if(rowNum%2==0){
                hidCell.setCellStyle(cellStyle2);
                staIdCell.setCellStyle(cellStyle2);
                userNameCell.setCellStyle(cellStyle2);
                userPhoneCell.setCellStyle(cellStyle2);
                sellPrice.setCellStyle(cellStyle2);
                sellDate.setCellStyle(cellStyle2);
                sellFinal.setCellStyle(cellStyle2);
            }else{
                hidCell.setCellStyle(cellStyle3);
                staIdCell.setCellStyle(cellStyle3);
                userNameCell.setCellStyle(cellStyle3);
                userPhoneCell.setCellStyle(cellStyle3);
                sellPrice.setCellStyle(cellStyle3);
                sellDate.setCellStyle(cellStyle3);
                sellFinal.setCellStyle(cellStyle3);
            }
        }


        //创建文件名
        String fileName = "龙湖地产销售记录表.xlsx";
        //创建输出流
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            // 获取mimeType
            ServletContext servletContext = request.getServletContext();
            String mimeType = servletContext.getMimeType(fileName);
            // 获取浏览器信息,对文件名进行重新编码
            fileName = FileUtils.filenameEncoding(fileName,request);
            //设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //写出文件
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(os!=null) os.close();
                if(workbook!=null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "POI导出成功!!!";
    }
    @Override
    public List<Map<String,Integer>> selectHouseId() {
        List<Map<String,Integer>> list = sellMapper.selectHouseId();
        return list;
    }

    /**
     * 获取详细信息
     * @param sellId
     * @return
     */
    @Override
    public Sell selectAll(Integer sellId) {
        return sellMapper.selectByPrimaryKey(sellId);
    }
}
