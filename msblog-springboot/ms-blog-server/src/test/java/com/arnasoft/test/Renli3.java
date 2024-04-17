package com.arnasoft.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Renli3 {
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
    public static void readNum() throws Exception {
        FileInputStream in = new FileInputStream("E:\\阜康农商银行\\2024风险保障金\\2024\\员工号姓名.xlsx");
        //通过输入流读取指定的Excel文件
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        XSSFSheet sheet = excel.getSheet("Sheet1");

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        HashMap<String, String> numList = new HashMap<>();

        for (int i = 1; i <= lastRowNum; i++) {
            //获取Sheet页中的行
            XSSFRow row = sheet.getRow(i);
            //获取行的第1个单元格（柜员号）
            XSSFCell cell1 = row.getCell(0);
            //设置单元格类型
            cell1.setCellType(CellType.STRING);
            //获取单元格中的文本内容
            String index = cell1.getStringCellValue();

            //获取行的第2个单元格（姓名）
            XSSFCell userName = row.getCell(1);
            //获取单元格中的文本内容
            String uName = userName.getStringCellValue();

            numList.put(uName, index);

        }
        readInitData(numList);

        System.out.println("numList:" + numList);

        //关闭资源
        in.close();
        excel.close();
    }

    public static void readInitData(HashMap<String, String> numList) throws Exception {
        FileInputStream in = new FileInputStream("E:\\阜康农商银行\\2024风险保障金\\2024\\2021年风险保障金返还表-bak.xls");
        //通过输入流读取指定的Excel文件
        HSSFWorkbook excel = new HSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        HSSFSheet sheet = excel.getSheet("表一 (调出)");

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        System.out.println(lastRowNum);
        int num = 0;
        for (int i = 1; i <= lastRowNum; i++) {
            //获取Sheet页中的行
            HSSFRow row = sheet.getRow(i);

            //获取行的第1个单元格（柜员号）
            HSSFCell cell1 = row.getCell(0);
            //设置单元格类型
            cell1.setCellType(CellType.STRING);
            //获取单元格中的文本内容
            String guiyuanhao = cell1.getStringCellValue();

            //获取行的第2个单元格（姓名）
            HSSFCell userName = row.getCell(1);
            //获取单元格中的文本内容
            String xingming = userName.getStringCellValue();

            Set<String> strings = numList.keySet();
            Iterator<String> it = strings.iterator();
            String elem = null;
            while (it.hasNext()) {
                elem = it.next();
                if (elem.contains(xingming)) {
                    System.out.println("匹配到的姓名：" + elem + "==========柜员号：" + guiyuanhao);
                    cell1.setCellValue(numList.get(elem));
                    num++;
                }
            }
        }

        System.out.println("num:" + num);

        FileOutputStream out = new FileOutputStream("E:\\阜康农商银行\\2024风险保障金\\2024\\2021年风险保障金返还表-bak2.xls");
        //通过输出流将内存中的Excel文件写入到磁盘上
        excel.write(out);

        //关闭资源
        out.flush();
        out.close();
        in.close();
        excel.close();
    }

    public static void main(String[] args) throws Exception {
//        write();

        readNum();
    }

}
