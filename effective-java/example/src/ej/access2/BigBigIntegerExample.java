package ej.access2;

import java.math.BigInteger;
import java.util.Random;

public class BigBigIntegerExample {
    public static void main(String[] args) {
        BigInteger bigInteger1 = new BigBigInteger("1234");
        System.out.println("bigInteger1.getClass() = " + bigInteger1.getClass());

        System.out.println("==================");

        BigInteger bigInteger2 = new BigBigInteger("5678");
        bigInteger2 = BigBigInteger.safeInstance(bigInteger2);
        System.out.println("bigInteger2.getClass() = " + bigInteger2.getClass());
    }
}

class BigBigInteger extends BigInteger {

    public static BigInteger safeInstance(BigInteger val) {
        if(val.getClass() == BigInteger.class) {
            return val;
        }
        return new BigInteger(val.toByteArray());
    }


    public BigBigInteger(byte[] val, int off, int len) {
        super(val, off, len);
    }

    public BigBigInteger(byte[] val) {
        super(val);
    }

    public BigBigInteger(int signum, byte[] magnitude, int off, int len) {
        super(signum, magnitude, off, len);
    }

    public BigBigInteger(int signum, byte[] magnitude) {
        super(signum, magnitude);
    }

    public BigBigInteger(String val, int radix) {
        super(val, radix);
    }

    public BigBigInteger(String val) {
        super(val);
    }

    public BigBigInteger(int numBits, Random rnd) {
        super(numBits, rnd);
    }

    public BigBigInteger(int bitLength, int certainty, Random rnd) {
        super(bitLength, certainty, rnd);
    }
}