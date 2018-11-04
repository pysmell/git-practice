package EnumStudy;
/*
* 枚举类型的父类
public abstract class Enum<E extends Enum<E>>
        implements Comparable<E>, Serializable {
//枚举成员的名称
private final String name;
//枚举成员的顺序，是按照定义的顺序，从0开始
private final int ordinal;
//构造方法
protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }
 public final int ordinal() {//返回枚举常量的序数
        return ordinal;
    }
 }
 public final String name() {//返回此枚举常量的名称，在其枚举声明中对其进行声明。
        return name;
    }
 public final boolean equals(Object other) {
        return this==other;//比较地址
    }
public final int hashCode() {
        return super.hashCode();
 }
public final int compareTo(E o) {//返回枚举常量的序数
    //是按照次序 ordinal来比较的
}
 public static <T extends Enum<T>> T valueOf(Class<T> enumType,  String name) { }
 public String toString() {
        return name;
    }
*
* */
public class Test4 {
}
