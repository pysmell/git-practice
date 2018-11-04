package 动态改变注解上的值;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class Test {

    @TestA(value = "a", name = "linqw")
    public static void main(String[] args) throws Exception {
        Method method = Test.class.getMethod("main", String[].class);
        TestA testA = method.getAnnotation(TestA.class);
        if (testA == null) {
            throw new RuntimeException("please add testA");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(testA);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");

      /*  for (Field field : value) {
            field.setAccessible(true);
            System.out.println(field.get(invocationHandler));
        }*/

        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        value.get(invocationHandler);
        String val = (String) memberValues.get("value");
        String name = (String) memberValues.get("name");
        System.out.println("value改变前：" + val);
        System.out.println("name改变前：" + name);
        val = "b";
        name = "hll";
        memberValues.put("value", val);
        memberValues.put("name", name);
        System.out.println("value改变后：" + testA.value());
        System.out.println("name改变后：" + name);

    }
}

