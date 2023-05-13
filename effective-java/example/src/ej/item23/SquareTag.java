package ej.item23;

public class SquareTag {

    enum Shape {
        RECTANGLE, CIRCLE, SQUARE
    }

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // case RECTANGLE
    double length;
    double width;

    // case CIRCLE
    double radius;

    // case SQUARE (new)
    double side;

    String maker;

    public String getMaker() {
        return maker;
    }

    // for Square --> 생성자 오버로딩?
//    public SquareTag(double side, String maker) {
//        shape = Shape.CIRCLE;
//        this.side = side;
//        this.maker = maker;
//    }

    // for Circle
    public SquareTag(double radius, String maker) {
        shape = Shape.CIRCLE;
        this.radius = radius;
        this.maker = maker;
    }

    // for RECTANGLE
    public SquareTag(double width, double length, String maker) {
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
            case SQUARE:
                return side * side;
            default:
                throw new AssertionError(shape);
        }
    }
}
