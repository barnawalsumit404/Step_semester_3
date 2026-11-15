package week1.practice_problems;

import java.io.IOException;
import java.util.Scanner;

public class JavaConceptsPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printStringExamples();
        printStringArray();
        printStringDetails("  Java practice  ");
        printAsciiCode('Z');
        handleUncheckedExceptions();
        handleCheckedException();
        readAndGreetUser(scanner);

        scanner.close();
    }

    private static void printStringExamples() {
        String stringLiteral = "Java";
        String stringObject = new String("Strings");
        String escapedMessage = "Use \\\"quotes\\\" and a new line\\ncarefully.";

        System.out.println("String literal: " + stringLiteral);
        System.out.println("String object: " + stringObject);
        System.out.println("Escape sequence: " + escapedMessage);
    }

    private static void printStringArray() {
        String[] concepts = {"Strings", "Arrays", "Exceptions"};
        System.out.println("String array:");
        for (String concept : concepts) {
            System.out.println(concept);
        }
    }

    private static void printStringDetails(String inputText) {
        String trimmedText = inputText.trim();
        String upperCaseText = trimmedText.toUpperCase();
        String replacedText = trimmedText.replace(" ", "-");

        System.out.println("Text: " + trimmedText);
        System.out.println("Length: " + trimmedText.length());
        System.out.println("Uppercase: " + upperCaseText);
        System.out.println("First character: " + trimmedText.charAt(0));
        System.out.println("Contains Java: " + trimmedText.contains("Java"));
        System.out.println("Replaced text: " + replacedText);
    }

    private static void printAsciiCode(char character) {
        int asciiCode = character;
        System.out.println("ASCII code of " + character + ": " + asciiCode);
    }

    private static void handleUncheckedExceptions() {
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException exception) {
            System.out.println("Unchecked exception handled: "
                    + exception.getClass().getSimpleName());
        }

        try {
            Integer.parseInt("abc");
        } catch (RuntimeException exception) {
            System.out.println("RuntimeException handled: "
                    + exception.getClass().getSimpleName());
        }
    }

    private static void handleCheckedException() {
        try {
            loadPracticeFile();
        } catch (IOException exception) {
            System.out.println("Checked exception handled: " + exception.getMessage());
        }
    }

    private static void loadPracticeFile() throws IOException {
        throw new IOException("Practice file is unavailable");
    }

    private static void readAndGreetUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        if (scanner.hasNextLine()) {
            String userName = scanner.nextLine();
            System.out.println("Hello, " + userName + "!");
        }
    }
}
