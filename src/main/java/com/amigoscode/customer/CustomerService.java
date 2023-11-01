
//this is a RESTful controller
package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.ResourceNotFoundException;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.amigoscode.customer.CustomerDao.*;
/* Annotate the class with @Service, it is automatically discovered by Spring through classpath scanning.
Spring then creates a bean for that class, and it can
 be injected into other Spring components such as controllers or other services.*/

@Service
public class CustomerService {
    // Define a private field of type CustomerDao

    private final CustomerDao customerDao;
    // Constructor to inject CustomerDao dependency


    public CustomerService( @Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
// Implementation of getAllCustomers method

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    // // Using Optional.orElseThrow to handle the case when a customer is not found



    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("customer with id [%s] not found".formatted(id)));
    }


    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        //check if email exsit
        String email = customerRegistrationRequest.email();
        if (customerDao.existsPersonWithEmail(email)) {
            throw new DuplicateResourceException("email [%s] already taken");
        } //or we add


        customerDao.insertCustomer(
                new Customer(customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                        )
       ) ;

    }


    public  void deleteCustomerById(Integer customerId) {
        if (!customerDao.existsPersonWithId(customerId)) {
            throw new ResourceNotFoundException("customer with id [%s] not found".formatted(customerId)
            );
                }
             customerDao.deleteCustomerById(customerId);
    }
    public void updateCustomer(Integer customerId,
                                CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);
            boolean changes =false;
         if (updateRequest.name() != null && !updateRequest.name().equals(customer))
         customer.setName(updateRequest.name());
         changes = true;



             if (updateRequest.email() !=null && !updateRequest.email().equals(customer))
                 if (customerDao.existsPersonWithEmail(updateRequest.email())) {
                     throw new DuplicateResourceException("email already taken");

                 }
            customer.setEmail(updateRequest.email());
                    changes = true;

            if (!changes) {
                throw new RequestValidationException("no data changes found");
            }
            customerDao.updateCustomer(customer);

    }



}
