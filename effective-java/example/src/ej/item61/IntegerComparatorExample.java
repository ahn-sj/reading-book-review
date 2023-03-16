package ej.item61;

import java.util.Comparator;

public class IntegerComparatorExample {
    public static void main(String[] args) {
        // Integer --> fail
//        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer i, Integer j) {
//                System.out.println("(i == j) : " + (i == j));
//                return (i < j) ? -1 : (i == j ? 0 : 1);
//            }
//        };
//        System.out.println("Integer compare result =  " +
//                naturalOrder.compare(new Integer(42), new Integer(42)));

        // int --> success
        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer iBoxed, Integer jBoxed) {
                int i = iBoxed;
                int j = jBoxed;

                System.out.println("(i == j) : " + (i == j));
                return (i < j) ? -1 : (i == j ? 0 : 1);
            }
        };
        System.out.println("Integer compare result = " +
                naturalOrder.compare(new Integer(42), new Integer(42)));
    }
}
