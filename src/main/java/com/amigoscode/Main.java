package com.amigoscode;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import org.aspectj.bridge.ICommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }

//    let's create a model of the Customer
@Bean
CommandLineRunner runner(CustomerRepository customerRepository){

    return args -> {
        Customer alex = new Customer(
                "alex",
                "alex@gmail.com",
                21

        );
        Customer jamila = new Customer(
                "jamila",

                "jamila@gmail.com",
                23

        );
        List<Customer> customers = List.of(alex, jamila);
        customerRepository.saveAll(customers);
    };
}
    //lets create a apps config context


}
