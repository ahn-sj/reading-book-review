package ej.item04;

public class FinalClassExample {
    public static void main(String[] args) {
        FinalClass finalClass = new FinalClass();

        FinalClassUtils.printHashCodeByFinalClass(finalClass);
        FinalClassUtils.printToStringByFinalClass(finalClass);
    }
}

class FinalClassUtils {
    public static void printHashCodeByFinalClass(FinalClass finalClass) {
        System.out.println(">>>> " + finalClass.hashCode());
    }

    public static void printToStringByFinalClass(FinalClass finalClass) {
        System.out.println(">>>> " + finalClass.toString());
    }
}

final class FinalClass {
    void printClass() {
        System.out.println(">> " + this.getClass());
    }
}
