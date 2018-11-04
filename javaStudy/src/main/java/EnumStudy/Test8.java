package EnumStudy;

public class Test8 {

    public static void main(String[] args) {

        IPhone iPhone4 = new IPhone(4);
        IPhone iPhone5 = new IPhone(5);
        System.out.println(iPhone4.compareTo(iPhone5));
        Android a2 = new Android(2), a3 = new Android(3);
        System.out.println(a2.compareTo(a3));
        //System.out.println(iPhone4.compareTo(a3));
    }
}
