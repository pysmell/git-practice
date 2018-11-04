package LeetCode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000
 * 输入: "babad" 输出: "bab" 注意: "aba"也是一个有效答案。
 */
public class LongestPalingdromeSolution {

    public static void main(String[] args) {

        String string = "abcda";
        new LongestPalingdromeSolution().longestPalindrome(string);
    }

    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {

            return s;

        }

        if (s.length() == 1) {
            return s;
        }

        String result = "";

        char[] chars = s.toCharArray();

        out:
        for (int i = 0; i < chars.length-1; i++) {

            for (int j = i+1; j < chars.length;j++) {

                if (chars[i] == chars[j]) {

                    String tempResult = "";

                    boolean flag = true;

                    for (int k = i+1, t = j-1; k < j; k++,t--) {

                        if (k <= t && chars[k] != chars[t]) {
                            flag = false;
                            break;
                        }

                    }
                    if (flag) {
                        tempResult = s.substring(i, j+1);
                        if (tempResult.length() > result.length()) {
                            result = tempResult;
                            if (result.length() == s.length()) {
                                break out;
                            }
                        }
                    }
                }
            }
        }
        if (result.length() == 0) {
            result = String.valueOf(chars[0]);
        }
        System.out.println(result);
        return result;

    }

}
