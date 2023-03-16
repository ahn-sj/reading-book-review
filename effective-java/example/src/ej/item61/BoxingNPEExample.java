package ej.item61;

public class BoxingNPEExample {

    private static Integer intVal;

    public static void main(String[] args) {
        System.out.println("intVal = " + intVal);

        if(intVal <= 0) {
            throw new IllegalStateException("0보다 큰 값이어야 합니다.");
        }
    }
}
