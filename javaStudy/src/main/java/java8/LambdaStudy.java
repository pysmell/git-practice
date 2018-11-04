package java8;

/**
 * lambda学习
 */
public class LambdaStudy {

    public static void main(String[] args) {

        int temp1 = 100;
        int temp2 = 50;
        int temp3 = addNum(temp1, temp2);
        System.out.println("temp3值" + temp3 );
        System.out.println("temp2值" + temp2 );
        System.out.println("temp1值" + temp1 );
    }

    public static Integer addNum(Integer temp1, Integer temp2 ) {

        int temp3 = temp1 + temp2;

        return temp3;
    }
}









































