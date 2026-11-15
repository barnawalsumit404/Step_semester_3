package week1.assignment_problems;

import java.io.IOException;
import java.util.Scanner;

public class JavaConceptsAssignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        demonstrateStrings();
        demonstrateStringArray();
        demonstrateStringMethods("  Java Programming  ");
        demonstrateAsciiCode('A');
        demonstrateExceptionTypes();
        demonstrateCheckedException();
        demonstrateUserInput(scanner);

        scanner.close();
    }

    private static void demonstrateStrings() {
        String literal = "Java";
        String createdWithConstructor = new String("Programming");
        String escapedText = "She said, \"Practice makes perfect.\"";

        System.out.println("String literal: " + literal);
        System.out.println("Created string: " + createdWithConstructor);
        System.out.println("Escape sequence: " + escapedText);
    }

    private static void demonstrateStringArray() {
        String[] topics = {"Strings", "Exceptions", "Arrays"};
        System.out.println("String array:");
        for (String topic : topics) {
            System.out.println("- " + topic);
        }
    }

    private static void demonstrateStringMethods(String text) {
        String trimmedText = text.trim();
        String upperText = trimmedText.toUpperCase();
        String replacedText = trimmedText.replace(" ", "-");

        System.out.println("Original: " + text);
        System.out.println("Length: " + trimmedText.length());
        System.out.println("Uppercase: " + upperText);
        System.out.println("First character: " + trimmedText.charAt(0));
        System.out.println("Contains Java: " + trimmedText.contains("Java"));
        System.out.println("Replaced: " + replacedText);
    }

    private static void demonstrateAsciiCode(char character) {
        int asciiCode = character;
        System.out.println("ASCII code of " + character + ": " + asciiCode);
    }

    private static void demonstrateExceptionTypes() {
        try {
            int[] values = {10, 20};
            System.out.println("Array value: " + values[2]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Unchecked exception handled: " + exception.getClass().getSimpleName());
        }

        try {
            Integer.parseInt("not-a-number");
        } catch (NumberFormatException exception) {
            System.out.println("RuntimeException handled: " + exception.getClass().getSimpleName());
        }

        try {
            throw new InvalidAssignmentException("Assignment data is invalid");
        } catch (AssignmentException exception) {
            System.out.println("Custom exception handled: " + exception.getMessage());
        }
    }

    private static void demonstrateCheckedException() {
        try {
            readAssignmentFile();
        } catch (IOException exception) {
            System.out.println("Checked exception handled: " + exception.getMessage());
        }
    }

    private static void readAssignmentFile() throws IOException {
        throw new IOException("Assignment file is not available");
    }

    private static void demonstrateUserInput(Scanner scanner) {
        System.out.print("Enter your name: ");
        if (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            System.out.println("Welcome, " + name + "!");
        }
    }

    static class AssignmentException extends Exception {
        AssignmentException(String message) {
            super(message);
        }
    }

    static class InvalidAssignmentException extends AssignmentException {
        InvalidAssignmentException(String message) {
            super(message);
        }
    }
}
