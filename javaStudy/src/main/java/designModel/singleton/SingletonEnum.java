package designModel.singleton;

public class SingletonEnum {

    private SingletonEnum() {
    }

    public enum InnerEnum {

        INSTANCE;

        private SingletonEnum singletonEnum;

        InnerEnum() {
            singletonEnum = new SingletonEnum();
        }

        public SingletonEnum getSingletonEnum() {
            return singletonEnum;
        }

    }

    public class InnerClass {

    }


}
