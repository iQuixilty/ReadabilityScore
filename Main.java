import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileContents = TextLib.readFile("TextDataFiles/Siddarta.txt");

        // ======= test of splitting into sentences ========
        ArrayList<String> sentences = TextLib.splitIntoSentences2(fileContents);

        System.out.println(sentences.size());
        System.out.println(getTotalWords(sentences));
        System.out.println(getTotalSyllables(sentences));
        System.out.println(calcReadabilityScore(fileContents));

        // ============= test of extracting words ===========
//        String testSentence = "Martha said \"I think pome means apple or something??\"";
//        ArrayList<String> words = TextLib.extractWords(testSentence);
//
//        System.out.println(testSentence);
//        System.out.println(words);

        // ================= test of syllables ==============
//        for (String word : words) {
//            System.out.println(word + " : " + TextLib.countSyllables(word));
//        }

    }

    public static long getTotalWords(ArrayList<String> sentences) {
        long numOfTotalWords = 0;
        for (String sentence : sentences) {
            ArrayList<String> words = TextLib.extractWords(sentence);
            numOfTotalWords += words.size();
        }
        return numOfTotalWords;
    }

    public static long getTotalSyllables(ArrayList<String> sentences) {
        long numOfTotalSyllables = 0;
        for (String sentence : sentences) {
            ArrayList<String> words = TextLib.extractWords(sentence);
            numOfTotalSyllables += getSyllablesIn(words);
        }
        return numOfTotalSyllables;
    }

    public static long getSyllablesIn(ArrayList<String> words) {
        long numOfSyllables = 0;
        for (String word : words) {
            numOfSyllables += TextLib.countSyllables(word);
        }
        return numOfSyllables;
    }

    public static double calcReadabilityScore(String text) {
        ArrayList<String> sentences = TextLib.splitIntoSentences2(text);
        long totalSentences = sentences.size();
        long totalWords = getTotalWords(sentences);
        long totalSyllables = getTotalSyllables(sentences);

        return 206.835 - (1.015 * (totalWords / totalSentences)) - (84.6 * (totalSyllables / totalWords));
    }
}
