package designModel.桥接模式.package1;

public class MysqlXml implements MySqlConversion {

    @Override
    public void conversion() {
        System.out.println("数据库：mysql XML转换");
    }

}
