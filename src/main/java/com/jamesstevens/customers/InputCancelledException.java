package com.jamesstevens.customers;

/**
 * Signals that the user has cancelled the current operation and wants to
 * return to the main menu.
 */
public class InputCancelledException extends RuntimeException {

    /**
     * Creates an exception representing a user-requested cancellation.
     */
    public InputCancelledException() {
        super("Input operation cancelled.");
    }
}
