package proxyStudy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        ServiceInterface serviceInterface = new ServiceInterfaceImpl();
        InvocationHandler InvocationHandler = new MyInvocationHandler(serviceInterface);
        ServiceInterface proxy = (ServiceInterface) Proxy.newProxyInstance(ServiceInterface.class.getClassLoader(), serviceInterface.getClass().getInterfaces(), InvocationHandler);
        proxy.println();




    }

}
