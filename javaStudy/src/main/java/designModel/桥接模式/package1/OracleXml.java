package designModel.桥接模式.package1;

public class OracleXml implements OracleConversion {
    @Override
    public void conversion() {
        System.out.println("数据库：oracle xml转换");
    }
}
