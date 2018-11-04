package designModel.singleton;

public class Test {

    public static void main(String[] args) {
        for (int i = 0;i<10;i++) {

            SingletonEnum singletonEnum = SingletonEnum.InnerEnum.INSTANCE.getSingletonEnum();
            System.out.println(singletonEnum.hashCode());
        }

    }

}
