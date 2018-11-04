package designModel.桥接模式.package2;

public interface ConversionType {

    //这里可以传入数据库类型，这样就可以在其里面进行判断，达到复用转换类型
    void conversion();

}
