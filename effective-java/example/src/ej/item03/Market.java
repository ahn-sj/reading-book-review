package ej.item03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Market {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Bottle bottle1 = Bottle.getInstance();
        Bottle bottle2 = Bottle.getInstance();
        // System.out.println(bottle1.fill(300));

        Constructor<? extends Bottle> bottleConstructor = bottle1.getClass().getDeclaredConstructor();
        bottleConstructor.setAccessible(true);

        Bottle newBottle = bottleConstructor.newInstance();

        System.out.println("bottle2.hashCode()   = " + bottle2.hashCode());
        System.out.println("bottle1.hashCode()   = " + bottle1.hashCode());
        System.out.println("newBottle.hashCode() = " + newBottle.hashCode());
    }
}

class Bottle {
    private static final Bottle INSTANCE = new Bottle();
    private int capacity;

    private Bottle() {
        capacity = 1000;
    }

    public static Bottle getInstance() {
        return INSTANCE;
    }

    public int fill(int capacity) {
        // 만약 물통의 용량을 바꾸고 싶다면..?
        if(this.capacity - capacity < 0) {
            throw new IllegalArgumentException("물이 넘칩니다.");
        }
        return this.capacity - capacity;
    }
}