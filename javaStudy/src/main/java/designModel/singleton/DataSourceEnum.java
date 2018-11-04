package designModel.singleton;

public enum DataSourceEnum {

    DATASOURCE;

}
/**
 * 序列化的时候只将DATASOURCE这个名称输出，反序列化的时候再通过这个名称，查找对应的枚举类型
 * 因此反序列化后的实例也会和之前被序列化的对象实例相同，感觉还有个原因是动态生成的实例属性，是
 * static的，为此序列化前后都是JVM中的值，因为static类属性
 */