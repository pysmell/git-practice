package designModel.桥接模式.package1;

public class OraclePdf implements OracleConversion {

    @Override
    public void conversion() {
        System.out.println("数据库：oracle pdf转换");
    }
}
