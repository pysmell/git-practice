package com.meiya.nettypackage9.java序列化反序列化;

/*
* 父类没有继承Serializable
* */
class Parent {

    int money;

    String home;

    String name;

    public Parent() {
    }

    public Parent(int money, String home, String name) {
        this.money = money;
        this.home = home;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "money=" + money +
                ", home='" + home + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
