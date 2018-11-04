package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 */
public class CombinationsSolution {

    public static void main(String[] args) {

        letterCombinations("234");
    }

/*
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        Map<String, List<String>> stringListMap = new HashMap<>();

        String[] strings = digits.split("");

        List list2 = new ArrayList();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        List list3 = new ArrayList();
        list3.add("d");
        list3.add("e");
        list3.add("f");

        List list4 = new ArrayList();
        list4.add("g");
        list4.add("h");
        list4.add("i");

        List list5 = new ArrayList();
        list5.add("j");
        list5.add("k");
        list5.add("l");

        List list6 = new ArrayList();
        list6.add("m");
        list6.add("n");
        list6.add("o");

        List list7 = new ArrayList();
        list7.add("p");
        list7.add("q");
        list7.add("s");

        List list8 = new ArrayList();
        list8.add("t");
        list8.add("u");
        list8.add("v");

        List list9 = new ArrayList();
        list9.add("w");
        list9.add("x");
        list9.add("y");
        list9.add("z");

        stringListMap.put("2", list2);

        stringListMap.put("3", list3);

        stringListMap.put("4", list4);

        stringListMap.put("5", list5);

        stringListMap.put("6", list6);

        stringListMap.put("7", list7);

        stringListMap.put("8", list8);

        stringListMap.put("9", list9);

        if (strings == null || strings.length == 0) {
            return result;
        }

        if (strings.length == 1) {

            return stringListMap.get(strings[0]);

        }

        int len = strings.length;

        for (int i = 0; i < len-1; i++) {

            int flag = 0;

            List<String> list = stringListMap.get(strings[i]);

            for (int j=i+1;j < len;j++) {

                List<String> list1 = stringListMap.get(strings[j]);

                for (int k=j;k>j&&k<len-1;k++) {

                }

            }

        }

        return result;
    }
*/
public static List<String> letterCombinations(String digits) {

    List<String> res = new ArrayList<String>();
    if (digits == null || digits.length() == 0) return res;
    Map<Character,String> map=new HashMap<>();
    map.put('0', "");
    map.put('1', "");
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");

    StringBuilder[] str=new StringBuilder[digits.length()];

    for(int i=0;i<digits.length();i++)
    {
        str[i]=new StringBuilder();
        str[i].append(map.get(digits.charAt(i)));
    }

    res.add("");
    for(int i=0;i<digits.length();i++)
    {
        List<String> newRes = new ArrayList<String>();
        for (String value : res){
            for(int j=0;j<str[i].length();j++)
            {
                newRes.add(value+str[i].charAt(j));
            }
        }
        res=newRes;
    }
    return res;
}
}
