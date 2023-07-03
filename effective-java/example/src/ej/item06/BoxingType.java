package ej.item06;

public class BoxingType {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        autoBoxing();
        System.out.println("auto boxing: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        nonAutoBoxing();
        System.out.println("non-auto boxing: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void autoBoxing() {
        Long sum = 0L;

        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }

    private static void nonAutoBoxing() {
        long sum = 0L;

        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
}
