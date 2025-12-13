import java.util.Scanner;

/**
 * Application entry point.
 *
 * Responsibilities:
 * - Initialize system components
 * - Run menu loop
 * - Delegate behavior to CustomerService
 */
public class Final_Execution {

    public static void main(String[] args) {

        // Shared Scanner instance
        Scanner scanner = new Scanner(System.in);

        // Core application components
        CustomerRepository repository = new CustomerRepository(100);
        Input input = new Input(scanner);
        CustomerService service =
                new CustomerService("Dynamic Customs", repository, input);

        // Program startup
        CustomerService.displayIntro();
        service.displayCompany();
        service.displayMenu();

        int choice;
        do {
            choice = input.readMenuChoice();

            switch (choice) {
                case 1 -> service.addMultipleCustomers();
                case 2 -> service.addSingleCustomer();
                case 3 -> service.displayAllCustomers();
                case 4 -> service.retrieveSpecificCustomer();
                case 5 -> service.retrieveCustomersByRange();
                case 9 -> service.exitNow();
                default -> System.out.println("Invalid selection.\n");
            }

            if (choice != 9) {
                service.displayMenu();
            }

        } while (choice != 9);

        scanner.close();
    }
}
