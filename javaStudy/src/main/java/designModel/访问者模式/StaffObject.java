package designModel.访问者模式;

import java.util.HashMap;

public class StaffObject {

    private HashMap<String, Staff> employees;

    public StaffObject() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(Staff e) {
        if (!(employees.containsKey(e.getName()))) {
            employees.put(e.getName(), e);
        }
    }

    public void removeEmployee(Staff e) {
        if (employees.containsKey(e.getName())) {
            employees.remove(e.getName());
        }
    }

    public void accept(Visitor v) {
         for (Staff e : employees.values()) {
             e.accept(v);
         }
    }
}














































