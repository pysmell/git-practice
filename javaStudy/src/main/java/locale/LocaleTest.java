package locale;

import java.util.Locale;

public class LocaleTest {

    public static void main(String[] args) {

        Locale[] ls = Locale.getAvailableLocales();

        for (Locale locale:ls) {
            System.out.println(locale);
        }

   /*     Locale locale = new Locale("es","US");
        Locale locale1 = new Locale("es","us");
        System.out.println(locale);
        System.out.println(locale1);*/
        Locale locale = new Locale("fr");
        System.out.println(locale);
    }

}
