
//this is a service layer
package com.amigoscode.customer;

import java.util.List;
import java.util.Optional;
// Define the CustomerDao interface

public interface CustomerDao {
    public List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer (Customer customer);
    boolean existsPersonWithEmail(String email);

    boolean existsPersonWithId(Integer id);
    void deleteCustomerById (Integer customerId);
    void updateCustomer (Customer update);

}
