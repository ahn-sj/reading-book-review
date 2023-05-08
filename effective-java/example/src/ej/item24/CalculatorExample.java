package ej.item24;

public class CalculatorExample {
    public static void main(String[] args) {
        Calculator.Operation plus = Calculator.Operation.PLUS;
        double result = plus.apply(10, 20);

        System.out.println("result = " + result); // result = 30.0
    }
}

class Calculator {
    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;
        // public static final Operation {oper} = new Operation

        public double apply(double x, double y) {
            switch (this) {
                case PLUS:
                    return x + y;
                case MINUS:
                    return x - y;
                case TIMES:
                    return x * y;
                case DIVIDE:
                    return x / y;
            }
            throw new AssertionError("Invalid Operation = " + this);
        }
    }
}
