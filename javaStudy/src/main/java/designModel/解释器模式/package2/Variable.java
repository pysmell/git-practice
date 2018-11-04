package designModel.解释器模式.package2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Variable extends Expression {

    /**
     * 表达式名
     */
    private String name;

    @Override
    public boolean interpret(Context context) {
        return context.lookup(this);
    }

    @Override
    public boolean equals(Object object) {

        if (object != null && object instanceof Variable) {
            return this.name.equals(((Variable) object).name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
