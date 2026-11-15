package week2.practice_problems;

import java.util.Scanner;

public class Problem3_FileExtensionValidator {
    public static String validateFileExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        if (dot < 0 || dot == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dot + 1);
        if (extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("docx")
                || extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(validateFileExtension(scanner.nextLine()));
        scanner.close();
    }
}
