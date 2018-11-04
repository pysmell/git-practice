/*
package StreamStudy;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

*/
/** Stream初体验
 1、Stream是元素的集合，这点看起来有些类似Iterator
 2、可以支持顺序和并行的对原Stream进行汇聚的操作
 可以把Stream当成一个高级版本的Iterator。原始版本的Iterator，用户只能一个一个的遍历元素并对其执行某些操作；
 高级版本的Stream，用户只要给出需要对其包含的元素执行什么操作，比如“过滤掉长度大于10的字符串”、“获取每个字符串的首字母”等，
 具体这些操作如何应用到每个元素上，就给Stream就好了
* *//*

public class Test1 {

    public static void main(String[] args) {
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        System.out.println(nums.stream().filter(num -> num != null).count());
        List list = new ArrayList();
        List list1 = new LinkedList();
    }
}
*/
