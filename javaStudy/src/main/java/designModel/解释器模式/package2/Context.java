package designModel.解释器模式.package2;


import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Variable, Boolean> map = new HashMap<>();

    public void assign(Variable var, boolean value) {
        map.put(var, value);
    }

    public boolean lookup(Variable variable) {

        Boolean value = map.get(variable);

        if (value == null) {
            throw new IllegalArgumentException();
        }

        return value;
    }






}



































