package designModel.桥接模式.package1;

public class SqlServerTxt implements SqlServerConversion {
    @Override
    public void conversion() {
        System.out.println("数据库：sql txt转换");
    }
}
