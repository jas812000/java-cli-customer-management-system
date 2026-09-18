package com.jamesstevens.customers;

import java.util.Scanner;

/**
 * Application entry point for the Java CLI Customer Management System.
 */
public class Main {

    /**
     * Creates the application dependencies and starts the customer-management
     * service.
     *
     * @param args command-line arguments; not used by this application
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CustomerRepository repository =
                    new CustomerRepository(100);
            Input input = new Input(scanner);
            CustomerService service =
                    new CustomerService(
                            "Dynamic Customs",
                            repository,
                            input);

            service.run();
        }
    }
}