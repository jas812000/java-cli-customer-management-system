package com.example.customers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    @Test
    void add_whenBelowCapacity_returnsTrue_andFindByIdWorks() {
        CustomerRepository repo = new CustomerRepository(2);

        boolean added = repo.add(new Customer("Alice", 12345, 100.0));

        assertTrue(added);
        assertEquals(1, repo.size());
        assertNotNull(repo.findById(12345));
    }

    @Test
    void add_whenAtCapacity_returnsFalse() {
        CustomerRepository repo = new CustomerRepository(1);
        assertTrue(repo.add(new Customer("Alice", 11111, 10.0)));

        boolean addedSecond = repo.add(new Customer("Bob", 22222, 20.0));

        assertFalse(addedSecond);
        assertTrue(repo.isFull());
        assertEquals(1, repo.size());
    }

    @Test
    void getAll_returnsDefensiveCopy() {
        CustomerRepository repo = new CustomerRepository(3);
        repo.add(new Customer("Alice", 11111, 10.0));

        List<Customer> all = repo.getAll();
        all.clear(); // should NOT affect repository

        assertEquals(1, repo.size());
        assertEquals(1, repo.getAll().size());
    }

    @Test
    void findById_whenMissing_returnsNull() {
        CustomerRepository repo = new CustomerRepository(2);
        repo.add(new Customer("Alice", 11111, 10.0));

        Customer missing = repo.findById(99999);

        assertNull(missing);
    }

    @Test
    void findBySalesRange_inclusiveBounds_returnsMatches() {
        CustomerRepository repo = new CustomerRepository(5);
        repo.add(new Customer("Low", 10001, 50.0));
        repo.add(new Customer("Mid", 10002, 100.0));
        repo.add(new Customer("High", 10003, 150.0));

        List<Customer> results = repo.findBySalesRange(50.0, 150.0);

        assertEquals(3, results.size());
    }

    @Test
    void findBySalesRange_noMatches_returnsEmptyList() {
        CustomerRepository repo = new CustomerRepository(5);
        repo.add(new Customer("Alice", 11111, 10.0));
        repo.add(new Customer("Bob", 22222, 20.0));

        List<Customer> results = repo.findBySalesRange(100.0, 200.0);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
