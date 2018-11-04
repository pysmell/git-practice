package designModel.桥接模式.package1;

public class MysqlTxt implements MySqlConversion {
    @Override
    public void conversion() {
        System.out.println("数据库：mysql txt转换");
    }
}
