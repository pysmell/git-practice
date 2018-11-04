package designModel.桥接模式.package2;

public class PdfConversion implements ConversionType {

    @Override
    public void conversion() {
        System.out.println("数据库：mysql pdf转换");
    }
}
