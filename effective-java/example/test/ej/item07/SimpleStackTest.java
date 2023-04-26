package ej.item07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleStackTest {
    @Test
    void 스택_크기_초과() throws Exception {
        SimpleStack stack = new SimpleStack();

        for (int i = 0; i < 16; i++) stack.push(i);

        System.out.println(stack.size()); // 16
        stack.push(111);
        System.out.println(stack.size()); // 33
    }

    @Test
    void gc_debug() throws Exception {
        SimpleStack stack = new SimpleStack();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }
    }
}