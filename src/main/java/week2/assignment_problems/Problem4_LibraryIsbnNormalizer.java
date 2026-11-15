package week2.assignment_problems;

import java.util.Scanner;

public class Problem4_LibraryIsbnNormalizer {
    public static String normalizeCode(String raw) {
        String code = raw.trim();
        if (code.length() < 3) {
            return code.toUpperCase();
        }
        return code.substring(0, 3).toUpperCase() + code.substring(3);
    }

    public static String validateAndFormat(String code) {
        String normalized = normalizeCode(code);
        if (normalized.length() != 13) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: body must be digits";
            }
        }

        StringBuilder result = new StringBuilder();
        result.append('[').append(normalized, 0, 3).append("] YEAR: ")
                .append(normalized, 3, 7).append(" | CATALOG: ")
                .append(normalized, 7, 13);
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(validateAndFormat(scanner.nextLine()));
        scanner.close();
    }
}
