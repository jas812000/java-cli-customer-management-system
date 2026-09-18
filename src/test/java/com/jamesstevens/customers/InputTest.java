package com.jamesstevens.customers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests console input handling and validation behavior.
 */
class InputTest {

    private final ByteArrayOutputStream output =
            new ByteArrayOutputStream();

    private PrintStream originalOutput;

    /**
     * Redirects console output before each test so validation messages
     * do not clutter the Maven test output.
     */
    @BeforeEach
    void redirectOutput() {
        originalOutput = System.out;
        System.setOut(new PrintStream(output));
    }

    /**
     * Restores the original console output after each test.
     */
    @AfterEach
    void restoreOutput() {
        System.setOut(originalOutput);
    }

    /**
     * Creates an Input instance backed by predefined console input.
     *
     * @param lines input values supplied to the scanner
     * @return input handler using the predefined values
     */
    private static Input inputFromLines(String... lines) {
        String joined = String.join("\n", lines) + "\n";
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        joined.getBytes(StandardCharsets.UTF_8));

        return new Input(new Scanner(in));
    }

    /**
     * Verifies that five-digit IDs may contain leading zeros.
     */
    @Test
    void readFiveDigitId_acceptsLeadingZeros() {
        Input input = inputFromLines("00012");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(12, id);
    }

    /**
     * Verifies that IDs with an invalid length are rejected before
     * a valid five-digit ID is accepted.
     */
    @Test
    void readFiveDigitId_rejectsWrongLength_thenAcceptsValid() {
        Input input =
                inputFromLines("1234", "123456", "12345");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(12345, id);
    }

    /**
     * Verifies that non-numeric IDs are rejected before
     * a valid numeric ID is accepted.
     */
    @Test
    void readFiveDigitId_rejectsNonNumeric_thenAcceptsValid() {
        Input input =
                inputFromLines("12a45", "ABCDE", "54321");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(54321, id);
    }

    /**
     * Verifies that integer input below the minimum is rejected
     * before a valid value is accepted.
     */
    @Test
    void readInt_enforcesMinimum_thenAcceptsValid() {
        Input input = inputFromLines("-1", "0", "2");

        int value = input.readInt("Count: ", 1);

        assertEquals(2, value);
    }

    /**
     * Verifies that invalid numeric input is rejected before
     * a valid double value is accepted.
     */
    @Test
    void readDouble_rejectsInvalid_thenAcceptsValid() {
        Input input = inputFromLines("nope", "12.5");

        double value = input.readDouble("Sales: ");

        assertEquals(12.5, value, 0.000001);
    }

    /**
     * Verifies that NaN and infinite values are rejected before
     * a valid finite value is accepted.
     */
    @Test
    void readDouble_rejectsNonFiniteValues_thenAcceptsValid() {
        Input input =
                inputFromLines("NaN", "Infinity", "-Infinity", "25.75");

        double value = input.readDouble("Sales: ");

        assertEquals(25.75, value, 0.000001);
    }

    /**
     * Verifies that negative values are rejected while zero
     * remains a valid non-negative value.
     */
    @Test
    void readNonNegativeDouble_rejectsNegative_thenAcceptsZero() {
        Input input = inputFromLines("-10.50", "0");

        double value =
                input.readNonNegativeDouble("Sales: ");

        assertEquals(0.0, value, 0.000001);
    }

    /**
     * Verifies that positive values are accepted by
     * non-negative double input.
     */
    @Test
    void readNonNegativeDouble_acceptsPositiveValue() {
        Input input =
                inputFromLines("125.50");

        double value =
                input.readNonNegativeDouble("Sales: ");

        assertEquals(125.50, value, 0.000001);
    }

    /**
     * Verifies that entering "exit" cancels the current input operation.
     */
    @Test
    void exitCommandCancelsInputOperation() {
        Scanner scanner = new Scanner("exit\n");
        Input input = new Input(scanner);

        assertThrows(
                InputCancelledException.class,
                () -> input.readName("Name: "));
    }
}
