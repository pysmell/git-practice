package designModel.访问者模式;

public class Test {

    public static void main(String[] args) {

        StaffObject staffObject = new StaffObject();

        staffObject.addEmployee(new Staff("张三", 3000f, 2, 1));
        staffObject.addEmployee(new Staff("李四", 4000f, 3, 1));
        staffObject.addEmployee(new Staff("王五", 6000f, 5, 1));

        staffObject.accept(new ConcreteVisitor());

    }

}
