package EnumStudy;

import java.util.EnumSet;

/*
EnumSet使用
EnumSet是一个抽象类，继承了AbstractSet类，其本质上就是一个Set。只不过，Enumset是要与枚举类型
一起使用的专用 Set 实现。枚举 set 中所有键都必须来自单个枚举类型，该枚举类型在创建 set 时显式或隐式地指定。
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
尽管JDK没有提供EnumSet的实现子类，但是EnumSet新增的方法都是static方法，而且这些方法都是用来创建
一个EnumSet的对象。因此可以看做是一个对枚举中的元素进行操作的Set，而且性能也很高。看下面的例子：
* */
public class Test7 {

    public static void main(String[] args) {

        EnumSet<FavouriteColor> enumSet = EnumSet.allOf(FavouriteColor.class);
        enumSet.remove(FavouriteColor.BLACK);
        enumSet.remove(FavouriteColor.BLUE);
        for (FavouriteColor favouriteColor : enumSet) {
            System.out.println(favouriteColor);
        }
    }
}
/*
* EnumSet不支持同步访问。实现线程安全的方式是：
Set<MyEnum> s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class));
* */