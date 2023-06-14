package ej.item05;

import java.util.Collections;
import java.util.List;

public class SpellCheckerSingleton {
    private static final Lexicon dictionary = new LatinLexicon();
//    public static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton(...);

    private SpellCheckerSingleton() {}

    public static boolean isValid(String word) {
        // ...
        return false;
    }

    public static List<String> suggestions(String type) {
         // ...
        return Collections.emptyList();
    }
}