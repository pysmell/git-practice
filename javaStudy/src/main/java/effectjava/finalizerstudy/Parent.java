package effectjava.finalizerstudy;

public class Parent {

    public static void main(final String[] args) throws Exception {
        doSth();
        System.gc();
        Thread.sleep(2000);
    }

    private static void doSth() {
        Child c = new Child();
        System.out.println(c);
    }

    @SuppressWarnings("unused")
    private final Object guardian = new Object() {

        @Override
        protected void finalize() {
            System.out.println("父类中匿名内部类--终结方法守卫者 重写的finalize()执行了");
            // 在这里调用Parent重写的finalize即可在清理子类时调用父类自己的清理方法
            parentlFinalize();
            // Parent.this.finalize(); 这样写不对，会执行Child重写的finalize()方法
        }
    };

    private void parentlFinalize() {
        System.out.println("父类自身的终结方法执行了");
        // 一些逻辑..
    }

    @Override
    protected void finalize() {
        parentlFinalize();
    }
}

class Child extends Parent {

    @Override
    protected void finalize() {

        System.out.println("子类finalize方法执行了，注意，子类并没有调用super.finalize()");
        // 由于子类（忘记或者其他原因）没有调用super.finalize()
        // 使用终结方法守卫者可以保证子类执行finalize()时(没有调用super.finalize())，父类的清理方法仍旧调用
        // "finally中显式调用super.finalize()"没被执行之后的另一种保障对象被及时销毁的措施
    }
}
