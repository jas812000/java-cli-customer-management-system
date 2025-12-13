import java.util.List;

/**
 * Coordinates application behavior.
 *
 * Responsibilities:
 * - Display menus and messages
 * - Orchestrate input, validation, and repository access
 * - Implement program use cases
 *
 * This class acts as the "service layer".
 */
public class CustomerService {

    private final String companyName;
    private final CustomerRepository repo;
    private final Input input;

    public CustomerService(String companyName,
                           CustomerRepository repo,
                           Input input) {
        this.companyName = companyName;
        this.repo = repo;
        this.input = input;
    }

    /** Displays the program purpose */
    public static void displayIntro() {
        System.out.println("This program displays a menu and allows the user to");
        System.out.println("add customers, view customers, search by ID,");
        System.out.println("retrieve customers by sales range, and exit.\n");
    }

    /** Displays the company name */
    public void displayCompany() {
        System.out.println("Welcome to " + companyName + "!!!\n");
    }

    /** Displays the main menu */
    public void displayMenu() {
        System.out.println("\tMENU");
        System.out.println("1: Add multiple new customers");
        System.out.println("2: Add single new customer");
        System.out.println("3: Display all customers");
        System.out.println("4: Retrieve specific customer data");
        System.out.println("5: Retrieve customers with orders based on range");
        System.out.println("9: Exit Program\n");
    }

    /**
     * Handles adding multiple customers in one operation.
     */
    public void addMultipleCustomers() {
        if (repo.isFull()) {
            System.out.println("Maximum customer capacity reached.\n");
            return;
        }

        int remaining = repo.capacity() - repo.size();
        int count = input.readInt("Enter number of customers to add: ", 1);

        count = Math.min(count, remaining);

        for (int i = 0; i < count; i++) {
            System.out.println("Customer " + (i + 1) + ":");
            String name = input.readName("\tName: ");
            int id = input.readFiveDigitId("\tID (5 digits): ");
            double sales = input.readDouble("\tTotal Sales: $ ");

            repo.add(new Customer(name, id, sales));
            System.out.println();
        }

        System.out.println("Successfully added " + count + " customers.\n");
    }

    /**
     * Adds a single customer.
     */
    public void addSingleCustomer() {
        if (repo.isFull()) {
            System.out.println("Maximum customer capacity reached.\n");
            return;
        }

        String name = input.readName("\tName: ");
        int id = input.readFiveDigitId("\tID (5 digits): ");
        double sales = input.readDouble("\tTotal Sales: $ ");

        repo.add(new Customer(name, id, sales));
        System.out.println("\nCustomer added successfully.\n");
    }

    /**
     * Displays all customers.
     */
    public void displayAllCustomers() {
        List<Customer> customers = repo.getAll();

        if (customers.isEmpty()) {
            System.out.println("No customers available.\n");
            return;
        }

        int index = 1;
        for (Customer c : customers) {
            System.out.println("Customer #" + index++);
            System.out.println("\tName: " + c.getName());
            System.out.println("\tID: " + String.format("%05d", c.getId()));
            System.out.println("\tTotal Sales: $" + c.getTotalSales() + "\n");
        }
    }

    /**
     * Retrieves a customer by ID.
     */
    public void retrieveSpecificCustomer() {
        if (repo.size() == 0) {
            System.out.println("No customers to search.\n");
            return;
        }

        int id = input.readFiveDigitId("\tEnter customer ID: ");
        Customer c = repo.findById(id);

        if (c == null) {
            System.out.println("Customer not found.\n");
            return;
        }

        System.out.println("\nCustomer Information:");
        System.out.println("\tName: " + c.getName());
        System.out.println("\tID: " + String.format("%05d", c.getId()));
        System.out.println("\tTotal Sales: $" + c.getTotalSales() + "\n");
    }

    /**
     * Retrieves customers within a sales range.
     */
    public void retrieveCustomersByRange() {
        double min = input.readDouble("\tMinimum sales: ");
        double max = input.readDouble("\tMaximum sales: ");

        if (max < min) {
            double temp = min;
            min = max;
            max = temp;
        }

        List<Customer> results = repo.findBySalesRange(min, max);

        if (results.isEmpty()) {
            System.out.println("No customers found in this range.\n");
            return;
        }

        for (Customer c : results) {
            System.out.println("\t" + c);
        }
        System.out.println();
    }

    /** Exits the program */
    public void exitNow() {
        System.out.println("\nHave a nice day!!!\nExiting now.............");
    }
}
