/*(CustomerController).
 The application follows the MVC (Model-View-Controller) architecture.*/
package com.amigoscode.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
/* Annotate the class with @RestController This Rest Controller class gives us
 JSON(JavaScript Object Notation) response as the output. */

@RestController
@RequestMapping("api/v1/customers")

public class CustomerController {
    // Define a private final field of type CustomerService

    private final CustomerService customerService ;
// Constructor to inject CustomerService dependency

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //let's have a method to create a controller Implementation of getCustomers endpoint
    // @RequestMapping(path = "api/v1/customer", method = RequestMethod.GET)
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }
    //lets create a second endpoint for the request by id
    @GetMapping(path = "{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomer(customerId);
       }

       @PostMapping
       public void registerCustomer(
               @RequestBody CustomerRegistrationRequest request) {
        customerService.addCustomer(request);
       }

       @DeleteMapping("{customerId}")
        public void deleteCustomer(
                @PathVariable("customerId") Integer customerId) {
                customerService.deleteCustomerById(customerId);
       }
    //this is the put method
       @PutMapping("{customerId}")
        public  void deleteCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestBody CustomerUpdateRequest updateRequest) {
        customerService.updateCustomer(customerId, updateRequest);
       }



}
