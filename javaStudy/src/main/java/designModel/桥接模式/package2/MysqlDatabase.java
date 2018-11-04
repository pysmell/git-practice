package designModel.桥接模式.package2;

public class MysqlDatabase extends DatabaseType {

    public MysqlDatabase(ConversionType conversionType) {
        super(conversionType);
    }

    @Override
    public void conversion() {
        conversionType.conversion();
    }

}
