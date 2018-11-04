package EnumStudy;

/*
  枚举成员，枚举类FavouriteColor里面的成员便都是枚举成员，
  枚举成员就是枚举类中，没有任何类型修饰，只有变量名，也不能赋值的成员
  先将上面的例子进行反编译一下：
public final class FavouriteColor extends Enum {
    public static final FavouriteColor RED;
    public static final FavouriteColor GREEN;
    public static final FavouriteColor BLACK;
    public static final FavouriteColor BLUE;
    public static final FavouriteColor WHITE;
    public static final FavouriteColor BROWN;
}
枚举成员都被处理成 public static final 的静态枚举常量。即上面例子的枚举成员都是 枚举类FavouriteColor 的实例
* */
public class Test2 {
}
