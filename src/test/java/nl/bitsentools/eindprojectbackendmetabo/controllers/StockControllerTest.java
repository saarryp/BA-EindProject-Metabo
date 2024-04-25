package nl.bitsentools.eindprojectbackendmetabo.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")

class StockControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown(){}

    @Test
    void getAllStocks() {
    }

    @Test
    void getOneStock() {
    }

    @Test
    void createStock() {
    }

    @Test
    void updateStock() {
    }

    @Test
    void deleteStock() {
    }
}