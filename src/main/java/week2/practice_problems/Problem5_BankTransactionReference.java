package week2.practice_problems;

import java.util.Scanner;

public class Problem5_BankTransactionReference {
    public static String normalizeReference(String raw) {
        String reference = raw.trim();
        if (reference.length() < 3) {
            return reference.toUpperCase();
        }
        return reference.substring(0, 3).toUpperCase() + reference.substring(3);
    }

    public static String validateAndFormat(String reference) {
        String normalized = normalizeReference(reference);
        if (normalized.length() != 14) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: body must be digits";
            }
        }

        StringBuilder formatted = new StringBuilder();
        formatted.append('[').append(normalized, 0, 3).append("] DATE: ")
                .append(normalized, 3, 5).append('/')
                .append(normalized, 5, 7).append('/')
                .append(normalized, 7, 9).append(" | SEQ: ")
                .append(normalized, 9, 14);
        return formatted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(validateAndFormat(scanner.nextLine()));
        scanner.close();
    }
}
