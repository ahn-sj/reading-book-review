package ej.item76;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapElement {
    public static void main(String[] args) {

        Map<Number, String> map = new TreeMap<>();
        map.put(1, "일번");
        map.put(5, "오번");
        map.put(100, "백번");
        map.put(1.3, "일점삼번");
    }
}
