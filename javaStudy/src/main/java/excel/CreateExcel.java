package excel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

public class CreateExcel {

    public static void main(String[] args) {

        try {
            //打开文件
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("F:\\test.xlsx"));

            //生成一个名为"第一页"的工作表，"0"表示第一页
            WritableSheet sheet = writableWorkbook.createSheet("第一页", 0);
            //构造（0，0）第一列、第一行单元格，以及单元格的内容为testtestclb
            Label label = new Label(0, 0, "testtestclb");
            //将单元格添加到工作表中
            sheet.addCell(label);
            //要保存的单元格
            Number number = new Number(1, 0, 555);
            Number number1 = new Number(2, 0, 666);
            Number number2 = new Number(3, 0, 777);
            sheet.addCell(number);
            sheet.addCell(number1);
            sheet.addCell(number2);

            //写入数据并关闭文件
            writableWorkbook.write();
            writableWorkbook.close();

        } catch (Exception e) {

        }

    }

}
