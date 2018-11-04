package org.fenixsoft.clazz;

public class TestCla {

    int x;

    public int inc() {
        try {
            x = 1;
            return x;
        } catch(Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

}
