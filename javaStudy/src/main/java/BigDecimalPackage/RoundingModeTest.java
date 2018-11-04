package BigDecimalPackage;

import java.math.RoundingMode;

public class RoundingModeTest {

    public static void main(String[] args) {
        RoundingMode roundingMode = RoundingMode.valueOf(0);
        System.out.println(roundingMode);
        RoundingMode roundingMode1 = RoundingMode.valueOf(1);
        System.out.println(roundingMode1);
        System.out.println(RoundingMode.valueOf("UP"));
        RoundingMode[] roundingModes = RoundingMode.values();
        for (RoundingMode roundingMode2 : roundingModes) {
            System.out.println(roundingMode2.toString());
        }
    }


}

/**
 * UP
 * 舍入模式从零开始。 始终在非零丢弃分数之前增加数字
 * 5.5 6 2.5 3 1.6 2 1.1 2 1.0 1 -1.0 -1 -1.1 -2 -1.6 -2 -2.5 -3 -5.5 -6
 * DOWN
 * 舍入模式向零舍入。 不要在丢弃的分数之前递增数字（即截断）。
 * 5.5 5 2.5 2 1.6 1 1.1 1 1.0 1 -1.0 -1 -1.1 -1 -1.6 -1 -2.5 -2 -5.5 -5
 * CEILING
 * 圆形模式向正无穷大转弯。如果为正，则表现为RoundingMode.UP;如果为负，则表现为
 * RoundingMode.DOWN（5.5->6，2.5->3,1.6->2,1.1->2,1.0->1,-1.0->-1,-1.1->-1,-1.6->-1,-2.5->-2,-5.5->-5）
 * FLOOR
 * 舍入模式向负无穷大转弯。如果为正，则表现为RoundingMode.DOWN;如果为负，表现为RoundingMode.UP
 * (5.5->5,2.5->2,1.6->1,1.1->1,1.0->1,-1.0->-1,-1.1->-2,-1.6->-2,-2.5->-3,-5.5->-6)
 * HALF_UP
 * 四舍五入模式向最近邻居转弯
 * 如果丢弃的分数大于等于0.5，RoundingMode.UP,否则表现为RoundingMode.DOWN
 * 5.5->6,2.5->3,1.6->2,1.1->1,1.0->1,-1.1->-1,-1.6->-2,-2.5->-3,-5.5->-6
 * HALF_DOWN
 * 如果丢弃的分数大于0.5,RoundingMode.UP,否则表现为RoundingMode.DOWN
 * 5.5->5,2.5->2,1.6->2,1.1->1,-1.0->-1,-1.1->-1,-1.6->-2,-2.5->-2
 * HALF_EVEN
 * 如果丢弃的分数的左边的数字是奇数，则采用RoundingMode.UP，如果丢弃的分数的左边的数字是偶数，则采用RoundingMode.DOWN
 * 5.5 6 2.5 2 1.6 2 1.1 1 1.0 1 -1.0 -1 -1.1 -1 -1.6 -2 -2.5 -2 -5.5 -6
 * 方法
 * static RoundingMode valueOf(int rm)
 * 返回RoundingMode对应于遗留整数舍入模式恒定对象BigDecimal 。
 * static RoundingMode valueOf(String name)
 * 以指定的名称返回此类型的枚举常量。
 * static RoundingMode[] values()
 * 按照它们声明的顺序返回一个包含此枚举类型常量的数组。
 */






















