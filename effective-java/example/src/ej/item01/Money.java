package ej.item01;

public class Money {
    private final int value;

    Money(int value) {
        this.value = value;
    }

    public static Money valueOf(int value) {
        return new Money(value);
    }
}
