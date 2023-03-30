package ej.item49;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationParameter {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {


        System.out.println(addText("스트링"));
//        printPoint(null);
//        getElement(-1);




    }

    private static String addText(String value) {
        return Objects.requireNonNull(value, "예외가 빵! 뭐땜시? " + value) + "는 스트링이다.";
    }

    private static String printPoint(Point point) {
        return "x, y = " + point.getX() + ", " + point.getY();
    }

    private static String getElement(int index) {
        return list.get(index);
    }
}
