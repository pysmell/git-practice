package designModel.中介者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介抽象类
 */
public abstract class Mediator {

    /**
     * 用于储存房东角色
     */
    protected List<Person> landlordList = new ArrayList<>();

    /**
     * 用于储存租客角色
     */
    protected List<Person> renterList = new ArrayList<>();

    /**
     * 中介者注册房东信息
     * @param person 房东实体
     */
    public void registerLandlord(Person person) {
        landlordList.add(person);
    }

    /**
     * 中介者注册求租者信息
     * @param person 求租者实体
     */
    public void registerRenter(Person person) {
        renterList.add(person);
    }

    public abstract void operation(Person person, String message);
}





























