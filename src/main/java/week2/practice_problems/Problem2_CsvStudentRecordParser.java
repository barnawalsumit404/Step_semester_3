package week2.practice_problems;

import java.util.Scanner;

public class Problem2_CsvStudentRecordParser {
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",", -1);
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: " + fields[0] + " | Roll No: " + fields[1]
                + " | Dept: " + fields[2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        parseStudentRecord(scanner.nextLine());
        scanner.close();
    }
}
