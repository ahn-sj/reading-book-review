package ej.item23;

public class FigureOld {

    enum Shape {
        RECTANGLE, CIRCLE
    }

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // case RECTANGLE
    double length;
    double width;

    // case CIRCLE
    double radius;

    String maker;

    public String getMaker() {
        return maker;
    }

    // for Circle
    FigureOld(double radius, String maker) {
        shape = Shape.CIRCLE;
        this.radius = radius;
        this.maker = maker;
    }

    // for RECTANGLE
    FigureOld(double width, double length, String maker) {
        shape = Shape.RECTANGLE;
        this.width = width;
        this.length = length;
        this.maker = maker;
    }

    double area() {
        switch (shape) {
            case RECTANGLE:
                return width * length;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
