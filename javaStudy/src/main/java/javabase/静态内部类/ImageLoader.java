package javabase.静态内部类;

import javax.naming.Context;

/**
 * 静态内部类的生命周期
 * 在外部类加载时，静态内部类并没有实例化也没有执行它的静态代码块，得出结论：静态内部类并不是一开始就创建的，它与
 * 静态成员不一样，并不能直接通过外部类名.内部类名的方式就可以直接访问并得到它的对象，通俗一点来
 * 说就是静态内部类跟正常的一个外部类一样，它需要创建才能有
 */
public class ImageLoader {

    private Context context;

    static {
        System.out.println("ImageLoader静态代码块");
    }

    public ImageLoader() {
        System.out.println("ImageLoader构造方法");
    }

    public Context getContext() {
        return context;
    }

    public ImageLoader(Builder builder) {
        this.context = builder.context;
    }

    public static class Builder {

        private Context context;

        static {
            System.out.println("Builder静态代码块");
        }

        public Builder(){
            System.out.println("Builder构造方法");
        }

        public Builder with(Context context) {
            this.context = context;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }

    public static void main(String[] args) {

        ImageLoader imageLoader = new ImageLoader();

        Builder builder = new ImageLoader.Builder();

        System.out.println(builder);

        Builder builder1 = new ImageLoader.Builder();

        System.out.println(builder1);
    }

}







































































