package ej.item38;

import java.util.function.BiFunction;

public enum LambdaOperation {

    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y);

    private final String symbol;
    private final BiFunction<Double, Double, Double> func;

    LambdaOperation(String symbol, BiFunction<Double, Double, Double> func) {
        this.symbol = symbol;
        this.func = func;
    }
}
