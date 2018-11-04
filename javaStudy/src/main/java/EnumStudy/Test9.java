package EnumStudy;

import java.util.EnumMap;

/*
* EnumMap使用
* */
public class Test9 {

    public static void main(String[] args) {

        EnumMap<FavouriteColor, Integer> enumMap = new EnumMap<>(FavouriteColor.class);
        enumMap.put(FavouriteColor.BLACK, 1);
        enumMap.put(FavouriteColor.BLUE, 2);
        enumMap.put(FavouriteColor.BROWN, 3);
        enumMap.put(FavouriteColor.GREEN, 4);
        System.out.println(enumMap.get(FavouriteColor.BLACK));
    }
}
