package EnumStudy;

/*
* 枚举类型是无法被子类继承扩展的，无法满足以下两种情况
* ①希望扩展原来的枚举类型中的元素
* ②希望使用子类对枚举类型中的元素进行分类
* 接口Food作为一个大类，3种枚举类型做为接口的子类；Food管理着这些枚举类型。对于枚举而言，
* 实现接口是使其子类化的唯一办法，所以嵌套在Food中的每个枚举类都实现了Food接口。
* 从而“所有这东西都是某种类型的Food”
* */
public interface Food {

    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPERSSO, TEA;
    }
    enum Dessert implements Food {
        FRUIT, GELATO, TIRAMISU;
    }


}
