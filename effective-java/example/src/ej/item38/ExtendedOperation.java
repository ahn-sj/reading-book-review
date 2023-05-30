package ej.item38;

import java.util.function.BiFunction;

public enum ExtendedOperation implements Operation {

    TIMES("^", (x, y) -> x * y),
    DIVIDE("%", (x, y) -> x / y);

    private final String symbol;
    private final BiFunction<Double, Double, Double> func;

    ExtendedOperation(String symbol, BiFunction<Double, Double, Double> func) {
        this.symbol = symbol;
        this.func = func;
    }

    @Override
    public double apply(double x, double y) {
        return func.apply(x, y);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
