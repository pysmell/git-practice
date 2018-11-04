import java.util.*;

public class Test3 {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        for (int i = 0;i < 100;i++) {
            if (i < 10) {
                map.put("0" + i, "djasj" + i);
            } else {
                map.put(i + "", "ssdsdjasj" + i);

            }
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

        System.out.println(map instanceof List);

        Map<String, Map> temp = new HashMap<>();
        temp.put("112", new HashMap());
        temp.put("113", new HashMap());

        List<Map> mapList = new ArrayList<>();

        mapList.addAll(temp.values());
        System.out.println(mapList);

        String tempString = "04";
        Integer tempInteger = Integer.valueOf(tempString);

        Boolean b1 = Boolean.valueOf(false);
        Boolean b2 = Boolean.valueOf(false);
        Boolean boolean1 = new Boolean(false);
        Boolean boolean2 = new Boolean(false);
        System.out.println(b1 == b2);
        System.out.println(b1.equals(b2));
        System.out.println(boolean1 == boolean2);
        System.out.println(boolean1.equals(boolean2));

        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.toString() == null);
        System.out.println("".equals(stringBuilder.toString()));


    }

}
