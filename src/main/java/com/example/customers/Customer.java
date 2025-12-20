package com.example.customers;

/**
 * Represents a single customer record.
 *
 * This class is a simple data model (POJO) that encapsulates
 * customer-related information and enforces immutability.
 *
 * Responsibilities:
 * - Store customer name
 * - Store validated 5-digit customer ID
 * - Store total sales amount
 */
public class Customer {

    // Customer's display name
    private final String name;

    // Five-digit numeric customer identifier
    private final int id;

    // Total sales associated with the customer
    private final double totalSales;

    /**
     * Constructs an immutable Customer instance.
     *
     * @param name       customer's name
     * @param id         5-digit numeric ID
     * @param totalSales total sales amount
     */
    public Customer(String name, int id, double totalSales) {
        this.name = name;
        this.id = id;
        this.totalSales = totalSales;
    }

    /** @return customer name */
    public String getName() {
        return name;
    }

    /** @return customer ID */
    public int getId() {
        return id;
    }

    /** @return total sales */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Provides a readable string representation of a customer.
     * Useful for debugging and logging.
     */
    @Override
    public String toString() {
        return "Name: " + name +
               ", ID: " + String.format("%05d", id) +
               ", Total Sales: $" + totalSales;
    }
}
