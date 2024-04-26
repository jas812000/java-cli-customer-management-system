import java.util.Scanner;
import java.io.PrintStream;
import java.util.Arrays;

public class Final_Execution {

    public static void main(String[] args) {

        Scanner stdinput = new Scanner(System.in);
        int selection01;

        // create new instance
        Final project = new Final("Dyanmic Customs", 100);

        //Display the purpose of the program
        Final.displayIntro();

        //Display company name
        project.displayCompany();

        // Display the menu
        project.displayMenu();

        do {
            System.out.print("\tSelect an option: \t");
            selection01 = stdinput.nextInt();

            switch (selection01) {

                //Add multiple new customers
                case 1 :	System.out.println();
                    System.out.println("\tYou have selected 'Add multiple new customers'. Processing.......");
                    System.out.println();
                    project.multiCustomer(selection01);
                    project.displayMenu();
                    break;

                //Add single new customers
                case 2 :	System.out.println();
                    System.out.println("\tYou have selected 'Add single new customer'. Processing.......");
                    System.out.println();
                    project.singleCustomer(selection01);
                    project.displayMenu();
                    break;

                //Add display all customers
                case 3 :	System.out.println();
                    System.out.println("\tYou have selected 'Display all customers'. Processing.......");
                    System.out.println();
                    project.displayAllCustomers();
                    project.displayMenu();
                    break;

                //Retrieve specific customer data
                case 4 :	System.out.println();
                    System.out.println("\tYou have selected 'Retrieve specific customer data'. Processing.......");
                    System.out.println();
                    project.specificData();
                    project.displayMenu();
                    break;

                //Retrieve customers with orders based on range
                case 5 :	System.out.println();
                    System.out.println("\tYou have selected retrieve customers with orders based on range. Processing.......");
                    System.out.println();
                    project.rangeData();
                    project.displayMenu();
                    break;

                //Exit the program
                case 9 :	project.exitNow();
                    break;

                default:	System.out.println();
                    System.out.println("\tYou have made an invalid selection. Try again.");
                    System.out.println();
                    project.displayMenu();

            }// End Switch

        } while (selection01 != 9);

        stdinput.close(); // Close scanner
    }//End Main
}//End Final_Execution
