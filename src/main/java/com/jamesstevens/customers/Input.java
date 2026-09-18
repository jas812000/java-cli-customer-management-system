package com.jamesstevens.customers;

import java.util.Scanner;

/**
 * Handles console input and validation for the application.
 *
 * <p>Centralizing input handling keeps validation rules consistent and
 * allows the application to use a single shared {@link Scanner}.</p>
 */
public class Input {

    private static final String CANCEL_COMMAND = "exit";

    private final Scanner in;

    /**
     * Creates an input handler using the supplied scanner.
     *
     * @param in scanner used to read console input
     */
    public Input(Scanner in) {
        this.in = in;
    }

    /**
     * Reads a numeric menu selection.
     *
     * @return integer menu choice
     */
    public int readMenuChoice() {
        while (true) {
            System.out.print("Select an option: ");
            String value = in.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.\n");
            }
        }
    }

    /**
     * Reads a non-empty customer name.
     *
     * @param prompt prompt displayed to the user
     * @return trimmed, non-empty name
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = in.nextLine().trim();

            checkForCancellation(name);

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Name cannot be blank.\n");
        }
    }

    /**
     * Reads an ID containing exactly five numeric digits.
     *
     * <p>Leading zeros are permitted. Because the returned value is an
     * integer, leading zeros are restored when the ID is displayed.</p>
     *
     * @param prompt prompt displayed to the user
     * @return validated customer ID
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public int readFiveDigitId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = in.nextLine().trim();

            checkForCancellation(value);

            if (value.length() != 5) {
                System.out.println(
                        "The ID entered must contain exactly 5 digits.\n");
                continue;
            }

            if (!value.chars().allMatch(Character::isDigit)) {
                System.out.println(
                        "The ID entered must contain only digits.\n");
                continue;
            }

            return Integer.parseInt(value);
        }
    }

    /**
     * Reads an integer greater than or equal to the specified minimum.
     *
     * @param prompt prompt displayed to the user
     * @param minInclusive minimum accepted value
     * @return validated integer
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public int readInt(String prompt, int minInclusive) {
        while (true) {
            System.out.print(prompt);
            String value = in.nextLine().trim();

            checkForCancellation(value);

            try {
                int parsed = Integer.parseInt(value);

                if (parsed >= minInclusive) {
                    return parsed;
                }

                System.out.println(
                        "Value must be >= " + minInclusive + ".\n");
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer.\n");
            }
        }
    }

    /**
     * Reads a finite numeric value.
     *
     * @param prompt prompt displayed to the user
     * @return validated finite double value
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = in.nextLine().trim();

            checkForCancellation(value);

            try {
                double parsed = Double.parseDouble(value);

                if (Double.isFinite(parsed)) {
                    return parsed;
                }

                System.out.println("Value must be a finite number.\n");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.\n");
            }
        }
    }

    /**
     * Reads a finite numeric value greater than or equal to zero.
     *
     * @param prompt prompt displayed to the user
     * @return validated non-negative double value
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public double readNonNegativeDouble(String prompt) {
        while (true) {
            double value = readDouble(prompt);

            if (value >= 0) {
                return value;
            }

            System.out.println("Value cannot be negative.\n");
        }
    }

    /**
     * Checks whether the user requested cancellation of the current operation.
     *
     * @param value raw trimmed input value
     * @throws InputCancelledException if the value is the cancellation command
     */
    private void checkForCancellation(String value) {
        if (CANCEL_COMMAND.equalsIgnoreCase(value)) {
            throw new InputCancelledException();
        }
    }
}
