package com.amigoscode.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("jdbc")

public class CustomerJDBCDataAccessService implements CustomerDao {

    private final  JdbcTemplate jdbcTemplate ;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return null;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return Optional.empty();
    }
    //in order to test this we have to change the qualifier from jpa to jdbc
    @Override
    public void insertCustomer(Customer customer) {
    var  sql ="""
              INSERT INTO customer(name, email, age )
              VALUES ( ?, ?, ?)
              """;

    int result = jdbcTemplate.update(
            sql,
            customer.getName(),
            customer.getEmail(),
            customer.getAge()

    );
        System.out.println("jdbcTemplate.update =" + result);


    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return false;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {

    }

    @Override
    public void updateCustomer(Customer update) {

    }
}
