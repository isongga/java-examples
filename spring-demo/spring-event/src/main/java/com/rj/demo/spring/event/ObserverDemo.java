package com.rj.demo.spring.event;

import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {

    static class EventObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println(arg.toString());
        }
    }

    public static void main(String[] args) {
        Observable observable = new Observable(){
            // 因为setChanged是protected的
            @Override
            public void setChanged() {
                super.setChanged();
            }

            @Override
            public void notifyObservers(Object arg) {
                setChanged();
                super.notifyObservers(arg);
                clearChanged();
            }
        };
        observable.addObserver(new EventObserver());

        observable.notifyObservers("hello");
    }
}
