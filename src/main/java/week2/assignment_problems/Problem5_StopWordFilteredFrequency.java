package week2.assignment_problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Problem5_StopWordFilteredFrequency {
    public static void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("was");
        stopWords.add("and");
        stopWords.add("a");
        stopWords.add("is");
        stopWords.add("of");
        stopWords.add("in");

        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "");
        String[] words = cleaned.split("\\s+");
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word)) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencies.entrySet());
        entries.sort(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed());
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder feedback = new StringBuilder();
        while (scanner.hasNextLine()) {
            if (feedback.length() > 0) {
                feedback.append(' ');
            }
            feedback.append(scanner.nextLine());
        }
        printFilteredWordFrequency(feedback.toString());
        scanner.close();
    }
}
