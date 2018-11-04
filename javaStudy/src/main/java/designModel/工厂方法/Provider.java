package designModel.工厂方法;

/**
 * 符合开闭原则，扩展，可以新增一个sender类实现Sender接口和一个Factory实现Provider
 * 缺点：只有产品接口，要是想要实现一个工厂创建产品族是不可能的
 */
public interface Provider {

    Sender produce();

}
