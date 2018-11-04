package designModel.适配器模式.接口的适配模式;

public class SourceSub2 extends Wrapper {
    @Override
    public void method2() {
        System.out.println("the sourceable interface's first Sub2");
    }
}
