package ej.item23;

public abstract class SquareHierarchy {
    final String maker;

    public SquareHierarchy(String maker) {
        this.maker = maker;
    }

    abstract double area();

    public String getMaker() {
        return maker;
    }
}

class Circle1 extends SquareHierarchy {

    final double radius;

    public Circle1(String maker, double radius) {
        super(maker);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle1 extends SquareHierarchy {

    final double length;
    final double width;

    public Rectangle1(String maker, double length, double width) {
        super(maker);
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return width * length;
    }
}

class Square extends SquareHierarchy {
    final double side;

    public Square(String maker, double side) {
        super(maker);
        this.side = side;
    }

    @Override
    double area() {
        return side * side;
    }
}
