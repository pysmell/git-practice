package designModel.访问者模式;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 */
@Getter
@Setter
@AllArgsConstructor
public strictfp class Staff extends BaseElement {

    private String name;

    private float salary;

    private int workAges;

    private int degree;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}















































