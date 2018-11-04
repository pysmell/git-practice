package designModel.访问者模式;

/**
 * @author linqw
 */
public strictfp class ConcreteVisitor implements Visitor {

    @Override
    public void visit(BaseElement element) {
        Staff e=(Staff) element;
        //比如：加薪的计算方式：职位*加薪系数+工龄*对应系统之和 除以10 再乘以现在工资
        System.out.println(e.getName()+"要加的薪水是："+(e.getDegree()*0.8+e.getWorkAges()*0.5)/10*e.getSalary());
    }
}

