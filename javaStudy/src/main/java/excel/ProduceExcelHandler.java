package excel;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 生成excelHandler
 *
 * @author linqw
 */
public class ProduceExcelHandler extends AbstractHandler {

    private static Logger logger = Logger.getLogger(ProduceExcelHandler.class);

    ProduceExcelHandler(int flag, Handler handler) {
        this.resolveFlag = flag;
        this.handler = handler;
    }

    @Override
    public void operator(int flag, File sourceFile, File targetFile) {
        if (flag == resolveFlag) {

            Properties properties = new Properties();
            try {
                readProperties(properties, sourceFile);
                //创建一个Excel对象
                XSSFWorkbook workbook = new XSSFWorkbook();
                //创建表单Sheet对象
                XSSFSheet sheet = workbook.createSheet();
                //创建行标Row对象
                XSSFRow row = sheet.createRow(0);
                //创建Cell对象，并进行写操作,对第一行进行操作
                XSSFCell cell = row.createCell(0);
                cell.setCellValue("平台");
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue("中文");
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue("允许最大长度（英文字母个数）");
                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue("法语");

                Set<Map.Entry<Object, Object>> entrys = properties.entrySet();
                Map<String, String> tempMap = new HashMap<>();
                int i = 1;
                for (Map.Entry<Object, Object> entry : entrys){
                    String key = ((String) entry.getKey()).replaceAll(" ", "");
                    String value = ((String) entry.getValue()).replaceAll(" ", "");
                    if (tempMap.containsKey(value)) {
                        continue;
                    } else {
                        tempMap.put(value, key);
                    }

                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(key);
                    cell1 = row.createCell(1);
                    cell1.setCellValue(value);
                    cell2 = row.createCell(2);
                    cell2.setCellValue("");
                    cell3 = row.createCell(3);
                    cell3.setCellValue("");
                    i++;
                }

                FileOutputStream output = new FileOutputStream(targetFile);
                workbook.write(output);
                output.flush();
            } catch (IOException e) {
                logger.error("源文件不是properties文件");
            }
        } else {
            if (handler != null) {
                handler.operator(flag, sourceFile, targetFile);
            } else {
                logger.error("源文件不是properties或excel文件");
            }
        }
    }

    /**
     * 读取配置文件的信息
     */
    private static void readProperties(Properties properties, File propertiesFile) throws IOException {

        //加载配置文件信息到Properties中  
        properties.load(new FileReader(propertiesFile));

    }
}
