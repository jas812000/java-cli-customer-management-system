package com.example.customers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    private static Input inputFromLines(String... lines) {
        String joined = String.join("\n", lines) + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(joined.getBytes(StandardCharsets.UTF_8));
        return new Input(new Scanner(in));
    }

    @Test
    void readFiveDigitId_acceptsLeadingZeros() {
        Input input = inputFromLines("00012");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(12, id); // int parsing drops leading zeros, which is fine
    }

    @Test
    void readFiveDigitId_rejectsWrongLength_thenAcceptsValid() {
        Input input = inputFromLines("1234", "123456", "12345");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(12345, id);
    }

    @Test
    void readFiveDigitId_rejectsNonNumeric_thenAcceptsValid() {
        Input input = inputFromLines("12a45", "ABCDE", "54321");

        int id = input.readFiveDigitId("ID: ");

        assertEquals(54321, id);
    }

    @Test
    void readInt_enforcesMinimum_thenAcceptsValid() {
        Input input = inputFromLines("-1", "0", "2");

        int value = input.readInt("Count: ", 1);

        assertEquals(2, value);
    }

    @Test
    void readDouble_rejectsInvalid_thenAcceptsValid() {
        Input input = inputFromLines("nope", "12.5");

        double value = input.readDouble("Sales: ");

        assertEquals(12.5, value, 0.000001);
    }
}
