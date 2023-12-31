//this is is a service layer

package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Annotate the class with @Repository

@Repository("list")

public class CustomerListDataAccessService implements CustomerDao  {
    // Define a temporary database (list of customers)
    private static final List<Customer> customers;

// Static block to initialize the database

    static {


        customers = new ArrayList<>();

        Customer alex = new Customer(
                1L,
                "alex",
                "alex@gmail.com",
                21

        );
        customers.add(alex);
        Customer jamila = new Customer(
                2L,
                "jamila",
                "jamila@gmail.com",
                23

        );
        customers.add(jamila);


    }
    // Implementation of selectAllCustomers method

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }
// Implementation of selectCustomerById method

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return  customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
                  }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
        .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return  customers.stream()
                .anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
              .ifPresent(customers::remove);
    }
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
