package designModel.观察者模式;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumeration = vector.elements();

        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().update();
        }
    }
}






















