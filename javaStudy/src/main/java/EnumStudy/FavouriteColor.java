package EnumStudy;

public enum FavouriteColor {

    //枚举成员
    RED,GREEN(2), BLACK(3), BLUE, WHITE, BROWN;

    //非枚举类型的成员
    private int colorValue;

    public int aa;

    public static final int cc = 2;

    private FavouriteColor() {
    }

    FavouriteColor(int colorValue) {
        this.colorValue = colorValue;
    }

    public void print() {
        System.out.println(colorValue);
    }
}











































