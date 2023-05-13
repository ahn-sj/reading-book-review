package ej.item23;


public abstract class FigureNew {
    final String maker;

    public FigureNew(String maker) {
        this.maker = maker;
    }

    abstract double area();

    public String getMaker() {
        return maker;
    }
}

class Circle extends FigureNew {

    final double radius;

    public Circle(String maker, double radius) {
        super(maker);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends FigureNew {

    final double length;
    final double width;

    public Rectangle(String maker, double length, double width) {
        super(maker);
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return width * length;
    }
}
