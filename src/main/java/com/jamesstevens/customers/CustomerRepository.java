package com.jamesstevens.customers;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the in-memory storage and retrieval of customer records.
 *
 * <p>The repository enforces a fixed capacity and prevents multiple
 * customers from being stored with the same customer ID.</p>
 */
public class CustomerRepository {

    private final int capacity;
    private final List<Customer> customers;

    /**
     * Creates an empty customer repository with the specified capacity.
     *
     * @param capacity maximum number of customers the repository can store
     * @throws IllegalArgumentException if capacity is less than 1
     */
    public CustomerRepository(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

        this.capacity = capacity;
        this.customers = new ArrayList<>();
    }

    /**
     * Returns the number of customers currently stored.
     *
     * @return current customer count
     */
    public int size() {
        return customers.size();
    }

    /**
     * Returns the maximum number of customers the repository can store.
     *
     * @return repository capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Determines whether the repository has reached its capacity.
     *
     * @return {@code true} if no additional customers can be stored
     */
    public boolean isFull() {
        return customers.size() >= capacity;
    }

    /**
     * Adds a customer when capacity is available and the customer ID is unique.
     *
     * @param customer customer to add
     * @return {@code true} if the customer was added; {@code false} if the
     *         repository is full or the customer ID already exists
     */
    public boolean add(Customer customer) {
        if (isFull() || findById(customer.getId()) != null) {
            return false;
        }

        customers.add(customer);
        return true;
    }

    /**
     * Returns a defensive copy of all stored customers.
     *
     * @return copy of the customer list
     */
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    /**
     * Finds a customer by ID.
     *
     * @param id customer ID
     * @return matching customer, or {@code null} if no customer has the ID
     */
    public Customer findById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }

    /**
     * Determines whether a customer with the specified ID exists.
     *
     * @param id customer ID to search for
     * @return {@code true} if a customer with the ID exists
     */
    public boolean containsId(int id) {
        return findById(id) != null;
    }

    /**
     * Finds customers whose total sales fall within the specified inclusive range.
     *
     * @param min minimum total sales
     * @param max maximum total sales
     * @return customers whose total sales are between {@code min} and {@code max},
     *         inclusive
     */
    public List<Customer> findBySalesRange(double min, double max) {
        List<Customer> results = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getTotalSales() >= min
                    && customer.getTotalSales() <= max) {
                results.add(customer);
            }
        }

        return results;
    }
}
