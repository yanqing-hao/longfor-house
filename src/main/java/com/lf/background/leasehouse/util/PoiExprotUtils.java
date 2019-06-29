package com.lf.background.leasehouse.util;

import com.lf.background.leasehouse.domain.Lease;

import com.lf.background.performance.domain.Performance;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2019/5/24.
 */
public class PoiExprotUtils {

    /**
     * 导出 学生信息
     * @param request
     * @param response
     */
    public void exportStuListExcel(HttpServletRequest request,
                                   HttpServletResponse response,
                                   List<Lease> LeaseList,
                                   String fileName) {



        //1 确定 导出版本 创建 excel 对象 创建一个excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2创建一个sheet
        XSSFSheet sheet = workbook.createSheet();

        //2.1 设置sheet名称
        workbook.setSheetName(0, "租赁房屋表");

//       3 创建一个 标题
        XSSFRow falstRow = sheet.createRow(0);
        //3.1合并单元格
        CellRangeAddress region = new CellRangeAddress((short)0,(short)0,(short)0, (short)4);//合并从第rowFrom行columnFrom列
        sheet.addMergedRegion(region);// 到rowTo行columnTo的区域

        XSSFCell falstRowCell = falstRow.createCell(0);
        falstRowCell.setCellValue("租赁");

        //设置样式
        // 字体样式
        XSSFFont xssfFont = workbook.createFont();
        // 加粗
        xssfFont.setBold(true);
        // 字体名称
        xssfFont.setFontName("宋体");
        // 字体大小
        xssfFont.setFontHeight(12);

        XSSFCellStyle headStyle = workbook.createCellStyle();
        // 设置字体css
        headStyle.setFont(xssfFont);
        // 竖向居中
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 横向居中
        headStyle.setAlignment(HorizontalAlignment.CENTER);

        //给单元格设置样式
        falstRowCell.setCellStyle(headStyle);






        //4创建创建表头
        XSSFRow row = sheet.createRow(1);
        //赋值单元格  创建表头  编号	姓名	年龄	生日	爱好	手机号
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
       // row.createCell(2).setCellValue("年龄");
       // row.createCell(3).setCellValue("生日");
        row.createCell(4).setCellValue("手机号");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(LeaseList.get(0).getStartTime());//这里填的是开始时间，还有其它时间怎么填
        //设置时间列宽  256分之一个字符宽度，所以宽度就是字符数X256
        sheet.setColumnWidth((short)3,(short) format.length()*256);
        //设置手机号列宽
        sheet.setColumnWidth((short)4,(short) LeaseList.get(0).getUserPhone().length()*256);

        //5 生成 动态数据
        for(Lease lease :LeaseList){

            //创建行
            int lastRowNum = sheet.getLastRowNum();

            XSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(lease.getLeaseId());
            dataRow.createCell(1).setCellValue(lease.getUserName());
            dataRow.createCell(2).setCellValue(lease.getUserPhone());
            /*这里填写的也是开始使时间*/
            if(lease.getStartTime()!= null) {
                XSSFCell cell = dataRow.createCell(3);
                format = sdf.format(lease.getStartTime());
                cell.setCellValue(format);
            }
            dataRow.createCell(4).setCellValue(lease.getUserPhone());

            //让偶数行 设置行高
            if(lease.getLeaseId() %2 == 0){
                //HSSFRow.Height和HeightInPoints，
                // 这两个属性的区别在于HeightInPoints的单位是点，而Height的单位是1/20个点，所所以Height的值永远是HeightInPoints的20倍
                dataRow.setHeightInPoints((float) 20);
//                dataRow.setHeight(20*20);
            }

        }


        //6创建一个文件名
        fileName +=".xlsx";

        //创建输出流 将文件 写到页面上
        //7.获取输出流对象
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();

            //8.获取mimeType
            ServletContext servletContext = request.getServletContext();
            String mimeType = servletContext.getMimeType(fileName);
            //9.获取浏览器信息,对文件名进行重新编码
            fileName = filenameEncoding(fileName, request);

            //10.设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //11.写出文件,关闭流
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                outputStream.close();

                workbook.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //对文件名做编码转换
    public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if(agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if(agent.contains ("Safari")) {
            filename = new String (filename.getBytes ("utf-8"),"ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

}
