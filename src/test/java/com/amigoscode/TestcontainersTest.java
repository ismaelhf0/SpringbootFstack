package com.amigoscode;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers

public class TestcontainersTest {
    @Container
    public static final PostgreSQLContainer<?>postgreSQLContainer =

        new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("amigoscode-dao-unit-test")
                .withUsername("amigoscode")
                .withPassword("password");

    @DynamicPropertySource

    public static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.dataSource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.dataSource.username", postgreSQLContainer::getUsername);
        registry.add("spring.dataSource.password", postgreSQLContainer::getPassword);
    }
    @Test
    void canStartPostgresDB() {
assertThat(postgreSQLContainer.isRunning()).isTrue();
assertThat(postgreSQLContainer.isCreated()).isTrue();
    }

@Test
    void canApplyDbMigrationWithFlyway(){
    Flyway flyway = Flyway.configure().dataSource(postgreSQLContainer.getJdbcUrl(),
            postgreSQLContainer.getUsername(),
            postgreSQLContainer.getPassword())
            .load();
    flyway.migrate();
    System.out.println();
}




}
