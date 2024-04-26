import java.util.Scanner;

public class Final {


    // attributes
    private int numCapacity; // Max quantity of customers
    public String companyName; // Name of company
    private int customers[]; //keeps track of customers
    public String customerName[]; //keeps inventory of the customers
    public int customerID[]; //keeps inventory of the customer's ID
    public double totalSales[]; //keeps inventory of the total sales
    private int numCustomers; //running tally of current customers


    // constructor
    public Final(String companyName, int capacity) {
        this.companyName = companyName;
        numCapacity = capacity;
        customers = new int[numCapacity];
        customerName = new String[numCapacity];
        customerID = new int[numCapacity];
        totalSales = new double[numCapacity];
        numCustomers = 0;
    }

    // methods

    // Method to display the purpose of the program
    public static void displayIntro() {
        // Purpose of the program
        System.out.println("This program display a menu and provide the user the option to add multiple new customers, ");
        System.out.println("add a single new customer, display all customers, retrieve specific customer data, retrieve ");
        System.out.println("customers with orders based on range, and exit the program.");
        System.out.println();
    }//End displayIntro


    // Method to display company name
    public void displayCompany() {
        System.out.println("Welcome to " + companyName + "!!!\n");
    }//End displayCompany


    // Method to display the menu
    public void displayMenu() {
        //System.out.println("Welcome to " + companyName + "!!!\n\n");
        System.out.println("\tMENU");
        System.out.println("1: Add multiple new customers \n2: Add single new customer \n3: Display all customers");
        System.out.println("4: Retrieve specific customer data \n5: Retrieve customers with orders based on range ");
        System.out.println("9: Exit Program");
        System.out.println();
    }//End displayMenu


    //Method to add multiple customers
    public void multiCustomer(int numClients) {
        Scanner stdinput = new Scanner(System.in);
        int i=0;

        // User input to determine how many new customers
        System.out.println("Enter the amount of new customers you wish to add: ");
        int numClients02 = stdinput.nextInt();
        System.out.println("You elected to add " + numClients02 + " new customers.");
        System.out.println();

        if ((numCustomers + numClients02) == 101) {
            System.out.println("Apologies. We already have the maximum amount of customers.");
            System.out.println();
        }//End if statement

        else {
            System.out.println("Enter the names, customer ID (5 digits) and total sales of each customer: ");
            System.out.println();
            for(i = 0; i < (numClients02); i++) {

                System.out.print("\tCustomer " + (i+1) + " name: "); // Request user input for customer names
                customerName[numCustomers] = stdinput.next();		// Populates the array with customer names

                boolean nonDigit = true;
                String input01;

                do {

                    System.out.print("\tCustomer ID: "); // Request user input for customer ID
                    input01 = stdinput.next(); //Request user input for customer ID

                    //If statement to ensure ID meets parameters of length and being numeric
                    if (input01.length() == 5) {

                        for (int a=0; a < input01.length(); a++) {

                            if (!Character.isDigit(input01.charAt(a))) {
                                System.out.println("The ID entered is not numerical. Please try again.");
                                System.out.println();
                                nonDigit = false;
                                break;
                            }//End if statement
                            else {
                                nonDigit = true;

                            }//End else statement
                        } // End For Loop

                    } // End if statement
                    else {
                        System.out.println("The ID entered does not meet the length requirements. Please try again.");
                        System.out.println();
                    }//End else statement

                } while (input01.length()!=5 || !nonDigit);
                int input001 = Integer.parseInt(input01);
                customerID[numCustomers] = input001;//Populates the array with customer ID

                System.out.print("\tTotal sales: $ "); // Request user input for total sales
                totalSales[numCustomers] = stdinput.nextDouble();		// Populates the array with total sales

                numCustomers++;
                System.out.println();

            }// End For Loop
            System.out.println("You have successfully added " + numClients02 + " customers");
            System.out.println();

        }//End else statement
    }//End multiCustomer


    //Method to add single customers
    public void singleCustomer(int numClients) {
        Scanner stdinput = new Scanner(System.in);
        int i=0;


        if ((numCustomers + 1) == 101) {
            System.out.println("Apologies. We already have the maximum amount of customers.");
            System.out.println();
        }//End if statement

        else {
            System.out.println("Enter the names, customer ID (5 digits) and total sales of each customer: ");
            System.out.println();
            for(i = 0; i < (1); i++) {

                System.out.print("\tCustomer " + (i+1) + " name: "); // Request user input for customer names
                customerName[numCustomers] = stdinput.next();		// Populates the array with customer names

                boolean nonDigit = true;
                String input01;

                do {

                    System.out.print("\tCustomer ID: "); // Request user input for customer ID
                    input01 = stdinput.next(); //Request user input for customer ID

                    //If statement to ensure ID meets parameters of length and being numeric
                    if (input01.length() == 5) {

                        for (int a=0; a < input01.length(); a++) {

                            if (!Character.isDigit(input01.charAt(a))) {
                                System.out.println("The ID entered is not numerical. Please try again.");
                                System.out.println();
                                nonDigit = false;
                                break;
                            }//End if statement
                            else {
                                nonDigit = true;

                            }//End else statement
                        } // End For Loop

                    } // End if statement
                    else {
                        System.out.println("The ID entered does not meet the length requirements. Please try again.");
                        System.out.println();
                    }//End else statement

                } while (input01.length()!=5 || !nonDigit);
                int input001 = Integer.parseInt(input01);
                customerID[numCustomers] = input001;//Populates the array with customer ID

                System.out.print("\tTotal sales: $ "); // Request user input for total sales
                totalSales[numCustomers] = stdinput.nextDouble();		// Populates the array with total sales

                numCustomers++;
                System.out.println();

            }// End For Loop
            System.out.println("You have successfully added a customer");
            System.out.println();

        }//End else statement
        return;
    }//End singleCustomer


    //Method to display information about all customers
    public void displayAllCustomers() {
        int i;

        if (numCustomers == 0) {
            System.out.println("Currently, there are no customers.");
            System.out.println();
        }
        else {
            // print all data
            for (i=0; i < numCustomers; i++) {
                System.out.println("Customer #" + (i+1));
                System.out.println("\tName: " + customerName[i]);
                System.out.println("\tID: "+ customerID[i]);
                System.out.println("\tTotal Sales: $" + totalSales[i]);
                System.out.println();
            }
        }
    }//End displayAllCustomers


    //Method to retrieve specific customer data
    public void specificData() {
        Scanner stdinput = new Scanner(System.in);

        boolean nonDigit = true;
        int index1 = -1;
        String idNum;

        if (numCustomers == 0) {
            System.out.println("Currently, there are no customers to perform a search.");
            System.out.println();
        }
        else {
            System.out.println("Retrieve Customer Data.");

            do {
                System.out.print("\tEnter the customer's ID: "); // Request user input for customer ID
                idNum = stdinput.next(); //Request user input for customer ID

                //If statement to ensure ID meets parameters of length and being numeric
                if (idNum.length() == 5) {
                    for (int a=0; a < idNum.length(); a++) {
                        if (!Character.isDigit(idNum.charAt(a))) {
                            System.out.println("The ID entered is not numerical. Please try again.");
                            System.out.println();
                            nonDigit = false;
                            break;
                        } //End if statement

                        else {
                            nonDigit = true;

                        } //End else statement
                    } // End For Loop
                } // End if statement

                else {
                    System.out.println("The ID entered does not meet the length requirements. Please try again.");
                    System.out.println();
                }
            } while (idNum.length()!=5 || !nonDigit);

            int idNums = Integer.parseInt(idNum);

            for(int i = 0; i < numCustomers; i++) {
                if(customerID[i] == idNums) {
                    index1 = i;
                    break;
                }
            }
            System.out.println();
            System.out.println("Listed below is the information associated with ID #" + idNum + " :");
            System.out.println("\tCustomer Name: " + customerName[index1] + "\n\tCustomer ID: " + customerID[index1] + "\n\tTotal Sales: $" + totalSales[index1]);
            System.out.println();

        }//End else statement
    }//End specificData


    //Method to retrieve customers with orders based on range
    public void rangeData() {

        Scanner stdinput = new Scanner(System.in);

        double min01, max01;
        int index3 = -1;
        int k = 0;

        if (numCustomers == 0) {
            System.out.println("Currently, there are no customers to perform a search.");
            System.out.println();
        }
        else {
            System.out.println("To retrieve customers with total sales within a specific range, enter the lower value followed by the higher value: ");
            System.out.print("\tMinimum value: \t");
            min01 = stdinput.nextDouble();
            System.out.print("\tMaximum value: \t");
            max01 = stdinput.nextDouble();
            System.out.println();

            System.out.println("The customers whom fall within $" + min01 + " and $" + max01 + " are listed below: ");
            System.out.println();
            for(k = 0; k < numCustomers; k++) {
                if(totalSales[k] >= min01 && totalSales[k] <= max01) {
                    index3 = k;
                    System.out.println("\tCustomer Name: " + customerName[index3] + "\tCustomer ID: " + customerID[index3] + "\tTotal Sales: $" + totalSales[index3]);
                } //End if statement
            }//End for loop
            System.out.println();
        }//End else statement
    }//End rangeData


    // Method to exit the program upon selection
    public void exitNow() {
        System.out.println();
        System.out.println("Have a nice day!!!\nExiting now.............");
    }//End exitNow

}//End Final

