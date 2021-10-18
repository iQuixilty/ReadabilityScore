import java.util.ArrayList;

public class StringMethods {
    public static boolean isLetter(String letter) {
        if (letter.length() != 1) return false;
        return "abcdefghijklmnopqrstuvwxyz".contains(letter.toLowerCase());
    }

    public static boolean isVowel(String letter) {
        if (letter.length() != 1) return false;
        return "aeiou".contains(letter.toLowerCase());
    }

    public static boolean isConsonant(String letter) {
        if (letter.length() != 1) return false;
        return "bcdfghjklmnpqrstvwxyz".contains(letter.toLowerCase());
    }

    public static boolean isWhiteSpaceChar(String character) {
        if (character.length() != 1) return false;
        return " \t\n".contains(character);
    }

    public static boolean hasVowelConsonantVowel(String str) {
        for (int i = 0; i < str.length() - 2; i++) {
            if (isVowel(str.substring(i, i + 1)) && isConsonant(str.substring(i + 1, i + 2)) && isVowel(str.substring(i + 2, i + 3)))
                return true;
        }
        return false;
    }

    public static int getNumSpaces(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isWhiteSpaceChar(str.substring(i, i + 1))) count++;
        }
        return count;
    }

    public static ArrayList<Integer> getSpaceIndexes(String str) {
        ArrayList<Integer> list = new ArrayList<Integer>(str.length());
        for (int i = 0; i < str.length(); i++) {
            if (isWhiteSpaceChar(str.substring(i, i + 1))) list.add(i);
        }
        return list;
    }

    public static String trim(String word) {
        int lf = getFirstWhiteSpace(word);
        int rt = getLastWhiteSpace(word);
        return word.substring(lf, rt + 1);
    }

    private static int getFirstWhiteSpace(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!isWhiteSpaceChar(word.substring(i, i + 1))) return i;
        }
        return 0;
    }

    private static int getLastWhiteSpace(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            if (!isWhiteSpaceChar(word.substring(i, i + 1))) return i;
        }
        return word.length();
    }

    public static String getFirstWord(String msg) {
        if (getNumSpaces(msg) == 0) return "";
        int firstChar = getFirstWhiteSpace(msg);
        int firstSpace = msg.indexOf(" ", firstChar + 1);
        return msg.substring(firstChar, firstSpace == -1 ? msg.length() : firstSpace);
    }

    public static ArrayList<String> splitOnSpace(String str) {
        ArrayList<String> res = new ArrayList<>(str.length());
        String newWord = "";
        while (str.length() > 0) {
            newWord = getFirstWord(str) == "" ? str : getFirstWord(str);
            res.add(newWord);
            str = str.substring(str.indexOf(newWord) + newWord.length());
            str = str.trim();
        }
        return res;
    }

    public static String cleanWord(String word) {
        int lf = getFirstLetter(word);
        int rt = getLastLetter(word);
        return word.substring(lf, rt + 1);
    }

    private static int getFirstLetter(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (isLetter(word.substring(i, i + 1))) return i;
        }
        return 0;
    }

    private static int getLastLetter(String word) {
        if (word.length() == 1) return 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            if (isLetter(word.substring(i, i + 1))) return i;
        }
        return word.length();
    }
}

