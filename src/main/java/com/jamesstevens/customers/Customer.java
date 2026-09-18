package com.jamesstevens.customers;

/**
 * Represents an immutable customer record containing a name, customer ID,
 * and total sales amount.
 */
public class Customer {

    private final String name;
    private final int id;
    private final double totalSales;

    /**
     * Creates a customer record.
     *
     * @param name customer's name
     * @param id five-digit customer ID
     * @param totalSales customer's total sales amount
     */
    public Customer(String name, int id, double totalSales) {
        this.name = name;
        this.id = id;
        this.totalSales = totalSales;
    }

    /**
     * Returns the customer's name.
     *
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's ID.
     *
     * @return customer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the customer's total sales.
     *
     * @return total sales amount
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Returns a formatted representation of the customer.
     *
     * @return formatted customer information
     */
    @Override
    public String toString() {
        return String.format(
                "Name: %s, ID: %05d, Total Sales: $%.2f",
                name,
                id,
                totalSales);
    }
}