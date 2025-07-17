package solutions.wordprocessor;

import java.util.HashMap;
import java.util.Map;

public class WordProcessor {

    private static int calculateWordScore(String word) {
        String lowerWord = word.toLowerCase();

        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : lowerWord.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        int score = 0;

        for (int count : charCounts.values()) {
            if (count > 1) {
                score += count;
            }
        }
        return score;
    }

    public static String findFirstWordWithMostRepeats(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String[] words = text.replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

        if (words.length == 0) {
            return "";
        }

        String bestWord = "";
        int maxScore = -1;

        for (String word : words) {
            if (word.isEmpty()) continue;

            int currentScore = calculateWordScore(word);
            if (currentScore > maxScore) {
                maxScore = currentScore;
                bestWord = word;
            }
        }

        return bestWord;
    }

    public static void main(String[] args) {
        String sentence1 = "Today, is the greatest day ever!";
        String sentence2 = "Hello apple pie";
        String sentence3 = "Simple test";

        System.out.printf("'%s' -> '%s'%n", sentence1, findFirstWordWithMostRepeats(sentence1));
        System.out.printf("'%s' -> '%s'%n", sentence2, findFirstWordWithMostRepeats(sentence2));
        System.out.printf("'%s' -> '%s'%n", sentence3, findFirstWordWithMostRepeats(sentence3));
    }
}
