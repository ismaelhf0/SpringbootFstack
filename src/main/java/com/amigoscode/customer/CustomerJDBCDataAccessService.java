package com.amigoscode.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("jdbc")

public class CustomerJDBCDataAccessService implements CustomerDao {

    private final  JdbcTemplate jdbcTemplate ;
    private final  customerRowMapper customerRowMapper;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate, com.amigoscode.customer.customerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        var sql = """
                SELECT id, email, name, age
                FROM customer
                         """;
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        var sql = """
                SELECT id, email, name, age
                FROM customer
                WHERE id = ?
                         """;

        return jdbcTemplate.query(sql, customerRowMapper, id)
                .stream()
                .findFirst();
    }
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
        var sql = """
                SELECT COUNT (id) FROM customer
                WHERE email = ?
                
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count  !=null && count > 0;



    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        var sql = """
                SELECT COUNT (id) FROM customer
                WHERE id = ?
                
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count  !=null && count > 0;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        var sql = """
                DELETE
                FROM customer
                WHERE id = ?
                         """;


    jdbcTemplate.update(sql, customerId);
        System.out.println("the customer " + customerId + " got deleted" );

    return;
    }

    @Override
    public void updateCustomer(Customer update) {
        if (update.getId() != null) {
            String sql= "UPDATE customer SET name = ? WHERE id = ? ";
            int result = jdbcTemplate.update(sql, update.getName(), update.getId());
            System.out.println("the customer update is  :  " + result  );
        }
        if (update.getAge() != null) {
            String sql= "UPDATE customer SET age = ? WHERE id = ? ";
            int result = jdbcTemplate.update(sql, update.getAge(), update.getId());
            System.out.println("the customer update is  :  " + result  );
        }
        if (update.getEmail() != null) {
            String sql= "UPDATE customer SET email = ? WHERE id = ? ";
            int result = jdbcTemplate.update(sql, update.getEmail(), update.getId());
            System.out.println("the customer update is  :  " + result  );
        }



    }
}
