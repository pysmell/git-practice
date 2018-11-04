package excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class PoiCreateExcel {

    public static void main(String[] args) throws IOException {

        //创建一个Excel对象
        XSSFWorkbook workbook = new XSSFWorkbook();

        //创建表单Sheet对象
        XSSFSheet sheet = workbook.createSheet();

        //创建Row对象
        XSSFRow row = sheet.createRow(0);
        XSSFRow row1 = sheet.createRow(1);
        XSSFRow row2 = sheet.createRow(2);

        //创建Cell对象，并进行写操作,对第一行进行操作
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("年龄");

        //第二行
        cell =  row1.createCell(0);
        cell.setCellValue("张三");
        cell1 =  row1.createCell(1);
        cell1.setCellValue("20");

        //第三行
        cell =  row2.createCell(0);
        cell.setCellValue("李四");
        cell1 =  row2.createCell(1);
        cell1.setCellValue("18");

        //输出文件
        FileOutputStream output = new FileOutputStream("F:\\test.xlsx");
        workbook.write(output);
        output.flush();

    }

}
