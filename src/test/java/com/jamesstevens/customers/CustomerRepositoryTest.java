package com.jamesstevens.customers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests customer repository capacity, storage, lookup, uniqueness,
 * defensive copying, and sales-range behavior.
 */
class CustomerRepositoryTest {

    /**
     * Verifies that repository capacity must be at least one.
     */
    @Test
    void constructor_whenCapacityIsLessThanOne_throwsException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new CustomerRepository(0));

        assertEquals("Capacity must be at least 1.", exception.getMessage());
    }

    /**
     * Verifies that a customer can be added when capacity is available
     * and can subsequently be found by ID.
     */
    @Test
    void add_whenBelowCapacity_returnsTrue_andFindByIdWorks() {
        CustomerRepository repo = new CustomerRepository(2);

        boolean added = repo.add(new Customer("Alice", 12345, 100.0));

        assertTrue(added);
        assertEquals(1, repo.size());
        assertNotNull(repo.findById(12345));
    }

    /**
     * Verifies that the repository rejects a customer when capacity
     * has already been reached.
     */
    @Test
    void add_whenAtCapacity_returnsFalse() {
        CustomerRepository repo = new CustomerRepository(1);

        assertTrue(repo.add(new Customer("Alice", 11111, 10.0)));

        boolean addedSecond =
                repo.add(new Customer("Bob", 22222, 20.0));

        assertFalse(addedSecond);
        assertTrue(repo.isFull());
        assertEquals(1, repo.size());
    }

    /**
     * Verifies that duplicate customer IDs are rejected and the
     * original customer remains unchanged.
     */
    @Test
    void add_whenIdAlreadyExists_returnsFalse() {
        CustomerRepository repo = new CustomerRepository(2);

        assertTrue(repo.add(new Customer("Alice", 12345, 100.0)));

        boolean duplicateAdded =
                repo.add(new Customer("Bob", 12345, 200.0));

        assertFalse(duplicateAdded);
        assertEquals(1, repo.size());
        assertEquals("Alice", repo.findById(12345).getName());
    }

    /**
     * Verifies that getAll() returns a defensive copy that cannot
     * modify the repository's internal customer list.
     */
    @Test
    void getAll_returnsDefensiveCopy() {
        CustomerRepository repo = new CustomerRepository(3);

        repo.add(new Customer("Alice", 11111, 10.0));

        List<Customer> all = repo.getAll();
        all.clear();

        assertEquals(1, repo.size());
        assertEquals(1, repo.getAll().size());
    }

    /**
     * Verifies that searching for an unknown customer ID returns null.
     */
    @Test
    void findById_whenMissing_returnsNull() {
        CustomerRepository repo = new CustomerRepository(2);

        repo.add(new Customer("Alice", 11111, 10.0));

        Customer missing = repo.findById(99999);

        assertNull(missing);
    }

    /**
     * Verifies that sales-range searches include customers whose
     * sales exactly match either boundary.
     */
    @Test
    void findBySalesRange_inclusiveBounds_returnsMatches() {
        CustomerRepository repo = new CustomerRepository(5);

        repo.add(new Customer("Low", 10001, 50.0));
        repo.add(new Customer("Mid", 10002, 100.0));
        repo.add(new Customer("High", 10003, 150.0));

        List<Customer> results =
                repo.findBySalesRange(50.0, 150.0);

        assertEquals(3, results.size());
    }

    /**
     * Verifies that a sales-range search with no matching customers
     * returns a non-null empty list.
     */
    @Test
    void findBySalesRange_noMatches_returnsEmptyList() {
        CustomerRepository repo = new CustomerRepository(5);

        repo.add(new Customer("Alice", 11111, 10.0));
        repo.add(new Customer("Bob", 22222, 20.0));

        List<Customer> results =
                repo.findBySalesRange(100.0, 200.0);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    /**
     * Verifies that containsId() correctly reports whether a customer ID exists.
     */
    @Test
    void containsId_returnsTrueForExistingId_andFalseForMissingId() {
        CustomerRepository repo = new CustomerRepository(2);

        repo.add(new Customer("Alice", 12345, 100.0));

        assertTrue(repo.containsId(12345));
        assertFalse(repo.containsId(99999));
    }
}
