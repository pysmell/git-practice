package designModel.桥接模式.package2;

public class TxtConversion implements ConversionType {

    @Override
    public void conversion() {
        System.out.println("数据库：mysql txt转换");
    }
}
