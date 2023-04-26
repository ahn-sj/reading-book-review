package ej.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class SimpleStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public SimpleStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object o) {
        ensureCapacity();
        elements[size++] = o;
    }

    public Object pop() {
        if(size == 0) {
            throw new EmptyStackException();
        }
        Object element = elements[--size];
        elements[size] = null;
        return element;
    }

    public int size() {
        return elements.length;
    }

    private void ensureCapacity() {
        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
