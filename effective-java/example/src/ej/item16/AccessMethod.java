package ej.item16;

import java.awt.*;

public class AccessMethod {
    public static void main(String[] args) {
        Point point = new Point(10, 20);
        point.x = 100;

        System.out.println("point = " + point.x + ", " + point.y);
    }
}