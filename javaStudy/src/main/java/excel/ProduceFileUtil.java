package excel;

import java.io.*;

public class ProduceFileUtil {

    private Handler producePropertiesHandler = new ProducePropertiesHandler(2, null);

    private Handler produceExcelHandler = new ProduceExcelHandler(1, producePropertiesHandler);

    public static void main(String[] args) throws IOException {

        ProduceFileUtil produceFileUtil = new ProduceFileUtil();

        //produceFileUtil.produceFile("F:\\temp\\messageResource_fr1.xlsx", "F:\\temp\\test.properties");
        produceFileUtil.produceFile("F:\\temp\\messageResource_zh_CN.properties", "F:\\temp\\messageResource.xlsx");
    }

    private void produceFile(String sourcePath, String targetPath) throws IOException {
        InputStream sourchInputStream = null;
        try {
            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);
            sourchInputStream = new FileInputStream(sourceFile);
            byte[] bytes = new byte[4];
            sourchInputStream.read(bytes, 0, bytes.length);
            String fileType = bytesToHexString(bytes);
            fileType = fileType.toUpperCase();
            String excelFileType = "504B0304";
            int flag = excelFileType.equals(fileType) ? 2 : 1;
            produceExcelHandler.operator(flag, sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sourchInputStream != null) {
                sourchInputStream.close();
            }
        }

    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
