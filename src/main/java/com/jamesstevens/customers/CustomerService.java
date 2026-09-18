package com.jamesstevens.customers;

import java.util.List;

/**
 * Coordinates customer-management use cases and console interaction.
 *
 * <p>The service controls the application menu, gathers validated input,
 * performs repository operations, and presents results to the user.</p>
 */
public class CustomerService {

    private final String companyName;
    private final CustomerRepository repo;
    private final Input input;

    /**
     * Creates a customer service.
     *
     * @param companyName company name displayed by the application
     * @param repo repository used to store customer records
     * @param input input handler used for validated console input
     */
    public CustomerService(
            String companyName,
            CustomerRepository repo,
            Input input) {

        this.companyName = companyName;
        this.repo = repo;
        this.input = input;
    }

    /**
     * Runs the customer-management application until the user chooses to exit.
     *
     * <p>Entering {@code exit} during a customer operation cancels that
     * operation and returns the user to the main menu.</p>
     */
    public void run() {
        displayIntro();
        displayCompany();

        int choice;

        do {
            displayMenu();
            choice = input.readMenuChoice();

            try {
                switch (choice) {
                    case 1 -> addMultipleCustomers();
                    case 2 -> addSingleCustomer();
                    case 3 -> displayAllCustomers();
                    case 4 -> retrieveSpecificCustomer();
                    case 5 -> retrieveCustomersByRange();
                    case 9 -> exitNow();
                    default -> System.out.println("Invalid selection.\n");
                }
            } catch (InputCancelledException e) {
                System.out.println(
                        "\nOperation cancelled. Returning to main menu.\n");
            }
        } while (choice != 9);
    }

    /**
     * Displays a brief description of the application.
     */
    private void displayIntro() {
        System.out.println(
                "This program displays a menu and allows the user to");
        System.out.println(
                "add customers, view customers, search by ID,");
        System.out.println(
                "retrieve customers by sales range, and exit.\n");
    }

    /**
     * Displays the company name.
     */
    private void displayCompany() {
        System.out.println("Welcome to " + companyName + "!\n");
    }

    /**
     * Displays the main application menu.
     */
    private void displayMenu() {
        System.out.println("\tMENU");
        System.out.println("1: Add multiple new customers");
        System.out.println("2: Add single new customer");
        System.out.println("3: Display all customers");
        System.out.println("4: Retrieve specific customer data");
        System.out.println("5: Retrieve customers by sales range");
        System.out.println("9: Exit Program\n");
    }

    /**
     * Displays the command for cancelling the current operation.
     */
    private void displayCancelOption() {
        System.out.println(
                "Type \"exit\" at any prompt to return to the main menu.\n");
    }

    /**
     * Adds multiple customers without exceeding repository capacity.
     */
    private void addMultipleCustomers() {
        if (repo.isFull()) {
            System.out.println(
                    "Maximum customer capacity reached.\n");
            return;
        }

        displayCancelOption();

        int remaining = repo.capacity() - repo.size();
        int count;

        do {
            count =
                    input.readInt(
                            "Enter number of customers to add: ",
                            1);

            if (count > remaining) {
                System.out.println(
                        "Only " + remaining
                                + " customer slot(s) remain.");
            }
        } while (count > remaining);

        int added = 0;

        while (added < count) {
            System.out.println("Customer " + (added + 1) + ":");

            String name =
                    input.readName("\tName: ");

            int id =
                    input.readFiveDigitId("\tID (5 digits): ");

            if (repo.containsId(id)) {
                System.out.println(
                        "\tA customer with that ID already exists. "
                                + "Try again.\n");
                continue;
            }

            double sales =
                    input.readNonNegativeDouble(
                            "\tTotal Sales: $");

            repo.add(new Customer(name, id, sales));
            added++;

            System.out.println();
        }

        System.out.println(
                "Successfully added "
                        + added
                        + " customer(s).\n");
    }

    /**
     * Adds one customer when repository capacity is available.
     */
    private void addSingleCustomer() {
        if (repo.isFull()) {
            System.out.println(
                    "Maximum customer capacity reached.\n");
            return;
        }

        displayCancelOption();

        String name =
                input.readName("\tName: ");

        int id;

        while (true) {
            id =
                    input.readFiveDigitId(
                            "\tID (5 digits): ");

            if (!repo.containsId(id)) {
                break;
            }

            System.out.println(
                    "\tA customer with that ID already exists. "
                            + "Try again.\n");
        }

        double sales =
                input.readNonNegativeDouble(
                        "\tTotal Sales: $");

        Customer customer =
                new Customer(name, id, sales);

        if (!repo.add(customer)) {
            System.out.println(
                    "\tUnable to add customer.\n");
            return;
        }

        System.out.println(
                "\nCustomer added successfully.\n");
    }

    /**
     * Displays all stored customers.
     */
    private void displayAllCustomers() {
        List<Customer> customers = repo.getAll();

        if (customers.isEmpty()) {
            System.out.println(
                    "No customers available.\n");
            return;
        }

        int index = 1;

        for (Customer customer : customers) {
            System.out.println(
                    "Customer #" + index++);

            System.out.println(
                    "\tName: " + customer.getName());

            System.out.printf(
                    "\tID: %05d%n",
                    customer.getId());

            System.out.printf(
                    "\tTotal Sales: $%.2f%n%n",
                    customer.getTotalSales());
        }
    }

    /**
     * Retrieves and displays a customer by ID.
     */
    private void retrieveSpecificCustomer() {
        if (repo.size() == 0) {
            System.out.println(
                    "No customers to search.\n");
            return;
        }

        displayCancelOption();

        int id =
                input.readFiveDigitId(
                        "\tEnter customer ID: ");

        Customer customer =
                repo.findById(id);

        if (customer == null) {
            System.out.println(
                    "Customer not found.\n");
            return;
        }

        System.out.println(
                "\nCustomer Information:");

        System.out.println(
                "\tName: " + customer.getName());

        System.out.printf(
                "\tID: %05d%n",
                customer.getId());

        System.out.printf(
                "\tTotal Sales: $%.2f%n%n",
                customer.getTotalSales());
    }

    /**
     * Retrieves and displays customers within an inclusive sales range.
     */
    private void retrieveCustomersByRange() {
        displayCancelOption();

        double min =
                input.readNonNegativeDouble(
                        "\tMinimum sales: $");

        double max =
                input.readNonNegativeDouble(
                        "\tMaximum sales: $");

        if (max < min) {
            double temp = min;
            min = max;
            max = temp;
        }

        List<Customer> results =
                repo.findBySalesRange(min, max);

        if (results.isEmpty()) {
            System.out.println(
                    "No customers found in this range.\n");
            return;
        }

        for (Customer customer : results) {
            System.out.println("\t" + customer);
        }

        System.out.println();
    }

    /**
     * Displays the application exit message.
     */
    private void exitNow() {
        System.out.println("\nHave a nice day!");
        System.out.println("Exiting now.");
    }
}
