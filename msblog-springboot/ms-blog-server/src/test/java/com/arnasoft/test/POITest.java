package com.arnasoft.test;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POITest {
    /**
     * 基于POI向Excel文件写入数据
     *
     * @throws Exception
     */
    public static void write() throws Exception {
        //在内存中创建一个Excel文件对象
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建Sheet页
        XSSFSheet sheet = excel.createSheet("itcast");

        //在Sheet页中创建行，0表示第1行
        XSSFRow row1 = sheet.createRow(0);
        //创建单元格并在单元格中设置值，单元格编号也是从0开始，1表示第2个单元格
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("城市");

        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(1).setCellValue("张三");
        row2.createCell(2).setCellValue("北京");

        XSSFRow row3 = sheet.createRow(2);
        row3.createCell(1).setCellValue("李四");
        row3.createCell(2).setCellValue("上海");

        FileOutputStream out = new FileOutputStream(new File("D:\\itcast.xlsx"));
        //通过输出流将内存中的Excel文件写入到磁盘上
        excel.write(out);

        //关闭资源
        out.flush();
        out.close();
        excel.close();
    }

    /**
     * 基于POI读取Excel文件
     *
     * @throws Exception
     */
    public static void read() throws Exception {
        FileInputStream in = new FileInputStream(new File("E:\\用户表2024-03-12-20-43-28.xlsx"));
        //通过输入流读取指定的Excel文件
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        XSSFSheet sheet = excel.getSheet("Sheet1");

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);
        for (int i = 1; i <= lastRowNum; i++) {
            //获取Sheet页中的行
            XSSFRow titleRow = sheet.getRow(i);
            //获取行的第1个单元格（序号）
            XSSFCell cell1 = titleRow.getCell(0);
            //设置单元格类型
            cell1.setCellType(CellType.STRING);
            //获取单元格中的文本内容
            String cellValue1 = cell1.getStringCellValue();

            //获取行的第2个单元格（真实姓名）
            XSSFCell cell2 = titleRow.getCell(1);
            //获取单元格中的文本内容
            String cellValue2 = cell2.getStringCellValue();

            //获取行的第3个单元格（用户名）
            XSSFCell cell3 = titleRow.getCell(2);
            //获取单元格中的文本内容
            String cellValue3 = cell3.getStringCellValue();

            //获取行的第4个单元格（身份证号码）
            XSSFCell cell4 = titleRow.getCell(3);
            //获取单元格中的文本内容
            String cellValue4 = cell4.getStringCellValue();

            //获取行的第5个单元格（份证号码）
            XSSFCell cell5 = titleRow.getCell(4);
            //获取单元格中的文本内容
            String cellValue5 = cell5.getStringCellValue();

            //获取行的第6个单元格（手机号）
            XSSFCell cell6 = titleRow.getCell(5);
            //获取单元格中的文本内容
            String cellValue6 = cell6.getStringCellValue();

            //获取行的第7个单元格（头像）
            XSSFCell cell7 = titleRow.getCell(6);
            //获取单元格中的文本内容
            String cellValue7 = cell7.getStringCellValue();

            //获取行的第8个单元格（性别）
            XSSFCell cell8 = titleRow.getCell(7);
            //获取单元格中的文本内容
            String cellValue8 = cell8.getStringCellValue();

            //获取行的第9个单元格（角色）
            XSSFCell cell9 = titleRow.getCell(8);
            //获取单元格中的文本内容
            String cellValue9 = cell9.getStringCellValue();

            //获取行的第10个单元格（添加时间）
            XSSFCell cell10= titleRow.getCell(9);
            //获取单元格中的文本内容
            String cellValue10 = cell10.getStringCellValue();

            System.out.println(cellValue1  +cellValue2 + " " +cellValue3 + " " +cellValue4
                    + " " +cellValue5 + " " +cellValue6 + " " +cellValue7 + " "
                    +cellValue8 + " " +cellValue9 + " " +cellValue10);
        }

        //关闭资源
        in.close();
        excel.close();
    }

    public static void main(String[] args) throws Exception {
//        write();

        read();
    }

}
