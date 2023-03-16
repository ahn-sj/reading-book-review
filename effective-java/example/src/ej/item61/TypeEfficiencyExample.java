package ej.item61;

public class TypeEfficiencyExample {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
//        Long sum = 0L;
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + ", time = " + (end - start) + "ms");
    }
}
