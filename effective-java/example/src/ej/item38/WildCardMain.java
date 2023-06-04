package ej.item38;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;

public class WildCardMain {
    public static void main(String[] args) {
        double x = 10.0;
        double y = 2.5;

//        EnumSet<Operation> operationSet = EnumSet.of(BasicOperation.PLUS, ExtendedOperation.DIVIDE); // ERROR
//        EnumMap<Operation, String> enumMap = new EnumMap<>(Operation.class);                         // ERROR
//        enumMap.put(BasicOperation.PLUS, BasicOperation.PLUS.toString());
//        enumMap.put(ExtendedOperation.DIVIDE, ExtendedOperation.DIVIDE.toString());

        test(Arrays.asList(BasicOperation.PLUS, ExtendedOperation.DIVIDE), x, y);
    }

    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet)
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
}


