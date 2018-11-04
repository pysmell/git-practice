import java.sql.SQLOutput;

public class Test4 {

    public static void main(String[] args) {

        int i = 0;
      /*  int j = 0;
        j = i++;*/
        i = i++;
        System.out.println(i);
        double d = add();
    }


    private static strictfp double add() {
        double d1 = 0.1;
        double d2 = 0.2;
        double sum = d1 + d2;
        System.out.println(sum);
        return sum;
    }
}
