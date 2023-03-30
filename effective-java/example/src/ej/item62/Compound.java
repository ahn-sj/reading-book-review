package ej.item62;

public class Compound {
    private CompoundKey compoundKey;

    private Compound(CompoundKey compoundKey) {
        this.compoundKey = compoundKey;
    }

    private static class CompoundKey {
        private String className;
        private String delimiter;
        private int index;

        public CompoundKey(String className, String delimiter, int index) {
            this.className = className;
            this.delimiter = delimiter;
            this.index = index;
        }

        public Compound of() {
            return new Compound(this);
        }

        @Override
        public String toString() {
            return "CompoundKey = {" + className + ", " + delimiter + ", " + index + "}";
        }
    }

    public static void main(String[] args) {
        Compound compound = new CompoundKey("class", "#", 10).of();

        System.out.println(compound.compoundKey);
    }
}