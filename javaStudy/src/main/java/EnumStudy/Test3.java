package EnumStudy;

/*
* 为枚举类型添加方法、构造器、非枚举的成员
* 注意事项：
* 枚举成员必须是最先声明，且只能用一行声明(相互间以逗号隔开，分号结束声明)
* 构造器的访问权限只能是private，可以不写，默认强制是private），不能是public、protected。
* */
public class Test3 {

    public static void main(String[] args) {
        FavouriteColor favouriteColor = FavouriteColor.RED;
        FavouriteColor favouriteColor1 = FavouriteColor.GREEN;
        FavouriteColor favouriteColor2 = FavouriteColor.BLACK;
        FavouriteColor favouriteColor3 = FavouriteColor.BLUE;
        FavouriteColor favouriteColor4 = FavouriteColor.WHITE;
        FavouriteColor favouriteColor5 = FavouriteColor.BROWN;
        System.out.print("red:");
        favouriteColor.print();
        System.out.print("GREEN:");
        favouriteColor1.print();
        System.out.print("BLACK:");
        favouriteColor2.print();
        System.out.print("BLUE:");
        favouriteColor3.print();
        System.out.print("WHITE:");
        favouriteColor4.print();
        System.out.print("BROWN:");
        favouriteColor5.print();
    }
}
/*
*
* GREEN(2), BLACK(3) 这两个枚举成员有点奇怪！
* 其实也很简答，前面说了，枚举成员其实就是枚举类型的实例，所以，GREEN(2), BLACK(3)
* 就是指明了用带参构造器，并传入参数，即可以理解成 FavouriteColor GREEN = new FavouriteColor(2)。
* 其他几个枚举类型则表示使用无参构造器来创建对象。( 事实上，编译器会重新创建每个构造器，为每个构造器多加两个参数)。
* */