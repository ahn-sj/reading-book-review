package ej.item05;

import java.util.List;

public class SpellCheckerDI {
    private final Lexicon dictionary;

    public SpellCheckerDI(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

//    public static boolean isValid(String word) {...}
//    public static List<String> suggestions(String type) {...}
}
