package week2.assignment_problems;

import java.util.Scanner;

public class Problem1_AtmPinLengthValidator {
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        checkPinLength(scanner.nextLine());
        scanner.close();
    }
}
