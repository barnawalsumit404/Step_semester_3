package week2.practice_problems;

import java.util.Scanner;

public class Problem1_VowelConsonantCounter {
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < text.length(); i++) {
            char character = Character.toLowerCase(text.charAt(i));
            if ("aeiou".indexOf(character) >= 0) {
                vowels++;
            } else if (character != ' ') {
                consonants++;
            }
        }
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        countVowelsAndConsonants(scanner.nextLine());
        scanner.close();
    }
}
