package ej.item24;

public class NestedLocalClassExample {
    public static void main(String[] args) {
        NestedLocalClass nestedLocalClass = new NestedLocalClass();
        nestedLocalClass.createLocalClass();
    }
}

class NestedLocalClass {
    public void createLocalClass() {
        class LocalClass {
            public LocalClass() {
                System.out.println("call LocalClass Constructor");
            }
        }
        LocalClass localClass = new LocalClass();
        System.out.println("localClass.getClass() = " + localClass.getClass());
    }
}
