package designModel.适配器模式.接口的适配模式;

public class SourceSub1 extends Wrapper {

    @Override
    public void method1() {
        System.out.println("the sourceable interface's first Sub1");
    }
}
