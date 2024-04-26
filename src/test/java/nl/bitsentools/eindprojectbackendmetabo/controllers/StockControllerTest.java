package nl.bitsentools.eindprojectbackendmetabo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")

class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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
    void createStock() throws Exception {
        String jsonInput = """
                {
                    "brandName": "Metabo",
                    "productName": "Metabo schuurmachine",
                    "productNumber": 102030,
                    "productInStock": 10,
                    "orderPlacedDate": "2024-01-14T10:30:00.000Z",
                    "weeksToDelivery": 16,
                    "quantityInStock": 10,
                    "typeOfMachine": "SCHUURMACHINE"
                }
                """;

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();


        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();
        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/stocks/" + createdId));


    }

    @Test
    void updateStock() throws Exception {

        String jsonInput = """
                {
                   "brandName": "MetaboSuper",
                               "productName": "Metabo SCHROEFMACHINE",
                               "productNumber": 123456,
                               "productInStock": 500,
                               "orderPlacedDate": "2024-04-28T09:00:00.000Z",
                               "weeksToDelivery": 1,
                               "quantityInStock": 1,
                               "typeOfMachine": "SCHROEFMACHINE"
                   
                   }
                   
                   """;

                    String stockIdUpdate = "1";
                    MvcResult result = mockMvc
                            .perform(MockMvcRequestBuilders.put("/stocks/" + stockIdUpdate)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonInput))
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andReturn();



    }

    @Test
    void deleteStock() {
    }
}