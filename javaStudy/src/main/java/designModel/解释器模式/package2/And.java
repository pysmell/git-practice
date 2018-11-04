package designModel.解释器模式.package2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class And extends Expression {

    Expression left, right;

    @Override
    public boolean interpret(Context context) {
        return left.interpret(context) && right.interpret(context);
    }

    @Override
    public boolean equals(Object object) {

        if(object != null && object instanceof And)
        {
            return left.equals(((And)object).left) &&
                    right.equals(((And)object).right);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}
