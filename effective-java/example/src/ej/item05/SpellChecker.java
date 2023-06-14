package ej.item05;

import java.util.List;

public class SpellChecker {
    private static final Lexicon dictionary = new LatinLexicon();

    private SpellChecker() {}

    public static boolean isValid(String word) {
//        if(참인 조건) {
//            return true;
//        }
        return false;
    }

//    public static List<String> suggestions(String type) {
        // ...
//    }
}

class Lexicon {}
class LatinLexicon extends Lexicon {}