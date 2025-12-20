package com.example.customers;

import java.util.Scanner;

/**
 * Handles all console input and validation.
 *
 * Responsibilities:
 * - Read menu selections
 * - Validate numeric input
 * - Enforce 5-digit ID rules
 *
 * Centralizing input logic prevents duplication
 * and avoids multiple Scanner instances.
 */
public class Input {

    private final Scanner in;

    /**
     * @param in shared Scanner instance
     */
    public Input(Scanner in) {
        this.in = in;
    }

    /**
     * Reads a valid menu selection.
     *
     * @return integer menu choice
     */
    public int readMenuChoice() {
        while (true) {
            System.out.print("\tSelect an option: \t");
            String input = in.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n\tInvalid input. Enter a number.\n");
            }
        }
    }

    /**
     * Reads a non-empty name string.
     *
     * @param prompt prompt text
     * @return validated name
     */
    public String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = in.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be blank.\n");
        }
    }

    /**
     * Reads and validates a 5-digit numeric ID.
     *
     * @param prompt prompt text
     * @return integer ID
     */
    public int readFiveDigitId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();

            if (s.length() != 5) {
                System.out.println("The ID entered does not meet the length requirements.\n");
                continue;
            }

            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    System.out.println("The ID entered is not numerical.\n");
                    s = null;
                    break;
                }
            }

            if (s != null) {
                return Integer.parseInt(s);
            }
        }
    }

    /**
     * Reads an integer greater than or equal to a minimum value.
     */
    public int readInt(String prompt, int minInclusive) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();

            try {
                int value = Integer.parseInt(s);
                if (value >= minInclusive) {
                    return value;
                }
                System.out.println("Value must be >= " + minInclusive + ".\n");
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer.\n");
            }
        }
    }

    /**
     * Reads a valid double value.
     */
    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();

            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.\n");
            }
        }
    }
}
