package nl.bitsentools.eindprojectbackendmetabo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import nl.bitsentools.eindprojectbackendmetabo.services.StockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")

class StockControllerTest {



    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

//    @BeforeEach
//    void setUp() throws Exception {
//
//        String jsonInput = """
//                {
//                    "brandName": "Metabo",
//                    "productName": "Metabo bitset",
//                    "productNumber": 123456,
//                    "productInStock": 20,
//                    "orderPlacedDate": "2024-04-28T09:00:00.000Z",
//                    "weeksToDelivery": 2,
//                    "quantityInStock": 50,
//                    "typeOfMachine": "BITSET"
//                }
//                """;
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/stocks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonInput))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//
//    }

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

        mockMvc.perform(MockMvcRequestBuilders.post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isCreated());


        // Update uitvoeren
        String updateJsonInput = """
            {
                "id" : 2,
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
        mockMvc.perform(put("/stocks/" + stockIdUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value("MetaboSuper"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Metabo SCHROEFMACHINE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(123456));
    }



//    @Test
//    void updateStock() throws Exception {
//
//        String jsonInput = """
//                {
//                    "id" : 1,
//                   "brandName": "MetaboSuper",
//                    "productName": "Metabo SCHROEFMACHINE",
//                     "productNumber": 123456,
//                      "productInStock": 500,
//                      "orderPlacedDate": "2024-04-28T09:00:00.000Z",
//                      "weeksToDelivery": 1,
//                      "quantityInStock": 1,
//                      "typeOfMachine": "SCHROEFMACHINE"
//
//                 }
//                """;
//
//                    String stockIdUpdate = "1";
//                    MvcResult result = mockMvc
//                            .perform(MockMvcRequestBuilders.put("/stocks/" + stockIdUpdate)
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(jsonInput))
//                            .andDo(MockMvcResultHandlers.print())
//                            .andExpect(MockMvcResultMatchers.status().isOk())
//                            .andReturn();
//
//        String jsonResponse = result.getResponse().getContentAsString();
//        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//
//        JsonNode idNode = jsonNode.get("id");
//        assertNotNull(idNode);
//        String createdId = idNode.asText();
//
////       assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/stocks/" + createdId));
//        assertEquals("MetaboSuper", jsonNode.get("brandName").asText());
//        assertEquals("Metabo SCHROEFMACHINE", jsonNode.get("productName").asText());
//        assertEquals(123456, jsonNode.get("productNumber").asInt());
//
//
//    }

//    @Test
//    void deleteStock() throws Exception{
//        mockMvc.perform(deleteStock("/stocks/" + );)
//    }
}