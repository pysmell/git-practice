package excel;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 生成Properties文件Handler
 *
 * @author linqw
 */
public class ProducePropertiesHandler extends AbstractHandler {

    private static Logger logger = Logger.getLogger(ProducePropertiesHandler.class);

    ProducePropertiesHandler(int flag, Handler handler) {
        this.resolveFlag = flag;
        this.handler = handler;
    }

    @Override
    public void operator(int flag, File sourceFile, File targetFile) {

        if (flag == resolveFlag) {

            try {
                Properties properties = new Properties();
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(sourceFile));
                //获取一个Sheet对象
                XSSFSheet sheet = workbook.getSheetAt(0);
                //总行数
                int rowNum = sheet.getLastRowNum();
                XSSFRow xssfRow;
                for (int i = 1; i < rowNum; i++) {
                    xssfRow = sheet.getRow(i);
                    properties.setProperty(xssfRow.getCell(0).getStringCellValue(), xssfRow.getCell(3).getStringCellValue());
                }
                properties.store(new FileWriter(targetFile), "");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (handler != null) {
                handler.operator(flag, sourceFile, targetFile);
            } else {

            }
        }

    }
}
