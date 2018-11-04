package proxyStudy;

import java.lang.reflect.Method;

public class MyInvocationHandler implements java.lang.reflect.InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("2222");
        method.invoke(target, args);
        System.out.println("3333");
        return null;
    }
}
