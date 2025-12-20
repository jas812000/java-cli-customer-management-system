package com.example.customers;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages storage and retrieval of Customer objects.
 *
 * Responsibilities:
 * - Enforce maximum capacity
 * - Add customers
 * - Retrieve customers by ID
 * - Retrieve customers by sales range
 *
 * This class contains NO user input or printing logic.
 */
public class CustomerRepository {

    // Maximum number of customers allowed
    private final int capacity;

    // Internal list used to store customer records
    private final List<Customer> customers;

    /**
     * Creates a repository with a fixed capacity.
     *
     * @param capacity maximum number of customers
     */
    public CustomerRepository(int capacity) {
        this.capacity = capacity;
        this.customers = new ArrayList<>();
    }

    /** @return current number of customers */
    public int size() {
        return customers.size();
    }

    /** @return maximum capacity */
    public int capacity() {
        return capacity;
    }

    /** @return true if repository has reached capacity */
    public boolean isFull() {
        return customers.size() >= capacity;
    }

    /**
     * Adds a customer if capacity allows.
     *
     * @param customer customer to add
     * @return true if added successfully
     */
    public boolean add(Customer customer) {
        if (isFull()) {
            return false;
        }
        customers.add(customer);
        return true;
    }

    /**
     * Returns a defensive copy of all customers.
     *
     * @return list of customers
     */
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    /**
     * Finds a customer by ID.
     *
     * @param id customer ID
     * @return matching customer or null if not found
     */
    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Retrieves customers whose total sales fall within a range.
     *
     * @param min minimum sales
     * @param max maximum sales
     * @return list of matching customers
     */
    public List<Customer> findBySalesRange(double min, double max) {
        List<Customer> results = new ArrayList<>();

        for (Customer c : customers) {
            if (c.getTotalSales() >= min && c.getTotalSales() <= max) {
                results.add(c);
            }
        }
        return results;
    }
}
