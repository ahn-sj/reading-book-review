package ej.item64;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;

public class OrderDependenceSet {
    public static void main(String[] args) {

        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        String rst1 = isOrderSet(hashSet);
        String rst2 = isOrderSet(linkedHashSet);

        System.out.println("rst1 = " + rst1);
        System.out.println("rst2 = " + rst2);
    }

    private static String isOrderSet(Set<Integer> set) {
        set.add(1000);
        set.add(1);
        set.add(10);
        set.add(100);

        StringBuilder sb = new StringBuilder();
        for (Integer integer : set) {
            sb.append(integer);
        }
        return sb + "";
    }
}
