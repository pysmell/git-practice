package designModel.解释器模式.package2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Constant extends Expression {

    private boolean value;

    @Override
    public boolean interpret(Context context) {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Variable) {
            return this.value == ((Constant) object).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return ((Boolean)value).toString();
    }
}
