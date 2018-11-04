package LeetCode;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class LengthOfLongestSubstringSolution {

    public static void main(String[] args) {

        String str = "dvdf";

        new LengthOfLongestSubstringSolution().lengthOfLongestSubstring(str);

    }

    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        char[] chars = s.toCharArray();

        String string = String.valueOf(chars[0]);

        String tempStr = String.valueOf(chars[0]);

        for (int i = 1; i < chars.length; i++) {

            if (chars[i] != chars[i-1]) {

                if (!(tempStr.indexOf(String.valueOf(chars[i])) >= 0)) {
                    tempStr += chars[i];
                } else {
                    if (tempStr.length() > string.length()) {
                        string = tempStr;
                    }

                    tempStr = tempStr.substring(tempStr.indexOf(String.valueOf(chars[i]))+1) + chars[i]+"";
                }

            } else {

                if (tempStr.length() > string.length()) {
                    string = tempStr;
                }

                tempStr = chars[i]+"";
            }

        }

        if (tempStr.length() > string.length()) {
            string = tempStr;
        }

        System.out.println(string);

        return string.length();
    }
}
