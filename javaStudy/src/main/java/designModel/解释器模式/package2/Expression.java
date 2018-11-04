package designModel.解释器模式.package2;

public abstract class Expression {

    public abstract boolean interpret(Context context);

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

}
