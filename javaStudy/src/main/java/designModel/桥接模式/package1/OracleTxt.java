package designModel.桥接模式.package1;

public class OracleTxt implements OracleConversion {
    @Override
    public void conversion() {
        System.out.println("数据库：oracle txt转换");
    }
}
