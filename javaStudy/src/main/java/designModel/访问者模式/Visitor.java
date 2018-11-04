package designModel.访问者模式;

public  interface Visitor {

    void visit(BaseElement element);

}
/**
 * 访问者模式,符合单一职责链，可扩展性好，把和元素类（staff）关系较小的操作，提取到访问者中，
 * 元素可以通过接受不同的访问者来实现对不同操作的扩展
 * 访问者模式把数据结构和作用于结构上的操作解耦合，使得操作集合可相对自由地演化。
 * 访问者模式适用于数据结构相对稳定算法又容易变化的系统。因为访问者模式使得算法操作变得容易。
 * 若系统数据结构容易变化，经常有新的数据对象增加进来，则不适合适用访问者模式。访问者模式的优点是增加操作很容易，可以在不改变元素类
 * 本身的前提下，实现对变化部分的扩展
 * 因为增加操作意味着增加新的访问者。访问者模式将有关行为集中到一个访问者对象中其改变不影响数据结构，其缺点就是增加新的数据结构（元素类）很困难
 *
 */































