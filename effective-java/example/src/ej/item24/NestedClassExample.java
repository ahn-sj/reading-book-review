package ej.item24;

public class NestedClassExample {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.NonStaticInner nonStaticInner1 = outer.createNonStaticInner();

        Outer.NonStaticInner nonStaticInner2 = new Outer().new NonStaticInner();


        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.callOuterValue();
    }
}

class Outer {
    private String ov = "Outer Class Variable";

    public class NonStaticInner {
        private String nsiv = "Non Static Inner Class Variable";

        public void callOuterValue() {
            System.out.println("call Outer private variable = " + Outer.this.ov);
        }
    }

    public NonStaticInner createNonStaticInner() {
        return new NonStaticInner();
    }

    public static class StaticInner {
        private String siv = "Static Inner Class Variable";

        public void callOuterValue() {
            Outer outer = new Outer();
            System.out.println("call Outer private variable = " + outer.ov);
        }
    }
}


