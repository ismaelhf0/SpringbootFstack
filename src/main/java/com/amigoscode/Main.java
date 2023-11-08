package com.amigoscode;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Random;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }

//    let's create a model of the Customer
@Bean
CommandLineRunner runner(CustomerRepository customerRepository){

    return args -> {
        for (int i = 0; i < 5 ; i++) {
            var faker =  new Faker();
            var name = faker.name();
            Random rAge = new Random();
            int result = rAge.nextInt(16,99);
            Name name1 = faker.name();
            String firstName = name1.firstName();
            String lastName = name1.lastName();
            Customer customer = new Customer(
                    firstName + " " +lastName,
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com",
                    result

            );



            customerRepository.save(customer);
        }

    };
}
    //lets create a apps config context


}
