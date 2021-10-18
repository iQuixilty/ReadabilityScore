import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TextLib {

    public static ArrayList<String> splitIntoSentences(String text) {
        text = text.replaceAll("[\\s]+", " "); // collapse whitespace

        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while (boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length() > 0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length() > 0)
            output.add(sentence);

        return output;
    }

    public static ArrayList<String> splitIntoSentences2(String text) {
        text = text.replaceAll("[\\s]+", " "); // collapse whitespace

        // remove punctuation
        String noPunctuationText = text.replaceAll("[^a-zA-Z -]", "");

        // splits when it sees a . ! or ? followed by whitespace
        String[] sentences = text.split("[.!?]+\\s+");

        // convert array into ArrayList
        return new ArrayList<String>(Arrays.asList(sentences));
    }

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath));) {

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }

        } catch (Exception errorObj) {
            System.err.println("There was a problem reading the " + filePath);
        }

        return sb.toString();
    }

    public static void writeToFile(String filePath, String data) {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {

            writer.print(data);

        } catch (Exception errorObj) {
            System.out.println("There was an error with the file");
            errorObj.printStackTrace();
        }
    }


    public static ArrayList<String> extractWords(String str) {
        ArrayList<String> res = new ArrayList<>(str.length());
        String newWord = "";
        while (str.length() > 0) {
            newWord = StringMethods.getFirstWord(str) == "" ? str : StringMethods.getFirstWord(str);
            res.add(StringMethods.cleanWord(newWord));
            str = str.substring(str.indexOf(newWord) + newWord.length());
        }
        return res;
    }

//    public static String getSyllables(String word) {
//
//    }

    public static int countSyllables(String word) {
        int numOfS = 0;
        for (int i = 0; i < word.length(); i++) {
            String curLet = word.substring(i, i + 1);
            if (StringMethods.isVowel(curLet)) {
                numOfS++;
                if (i + 1 != word.length() && StringMethods.isVowel(word.substring(i + 1, i + 2))) numOfS--;
            }
            if (i + 1 == word.length() && StringMethods.isVowel(curLet)) numOfS--;
        }

        return numOfS;
    }

}
