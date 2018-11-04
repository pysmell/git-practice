package designModel.桥接模式.package2;

public class Test {

    public static void main(String[] args) {

        ConversionType conversionType = new PdfConversion();

        DatabaseType databaseType = new MysqlDatabase(conversionType);

        databaseType.conversion();
    }


}
