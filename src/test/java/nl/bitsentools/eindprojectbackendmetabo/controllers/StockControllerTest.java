package nl.bitsentools.eindprojectbackendmetabo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnError;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import nl.bitsentools.eindprojectbackendmetabo.services.StockService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
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

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockControllerTest {



   @Autowired
   private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    //of moet ik de before each zo maken?
    StockModel stock1;
    StockModel stock2;

   @BeforeEach
    public void setUp(){
       stock1 = new StockModel(1L, "Metabo", "Metabo schuurmachine", 102030,
               10, LocalDate.of(2024, 1,14),
               16, 0, 10, false, TypeOfMachine.SCHUURMACHINE);

       stock2 = new StockModel(2L, "Haikoki", "Metabo zaagmachine", 302010,
               100, LocalDate.of(2019, 10,30 ),
               12, 2, 0, true, TypeOfMachine.ZAAGMACHINE);


       stockRepository.save(stock1);
       stockRepository.save(stock2);
    }

        @AfterEach
        void cleanUp() {
            stockRepository.deleteById(stock1.getId());
            stockRepository.deleteById(stock2.getId());
        }




    @Test
    @Order(1    )
    void getAllStocks() throws Exception {
        mockMvc.perform(get("/stocks"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(stock1.getId().toString()))
                .andExpect(jsonPath("$[0].brandName").value("Metabo"))
                .andExpect(jsonPath("$[0].productName").value("Metabo schuurmachine"))
                .andExpect(jsonPath("$[0].productNumber").value(102030))
                .andExpect(jsonPath("$[0].productInStock").value(10))
                .andExpect(jsonPath("$[0].orderPlacedDate").value("2024-01-14"))
                .andExpect(jsonPath("$[0].weeksToDelivery").value(16))
                .andExpect(jsonPath("$[0].productSold").value(0))
                .andExpect(jsonPath("$[0].quantityInStock").value(10))
                .andExpect(jsonPath("$[0].outOfStock").value(false))
                .andExpect(jsonPath("$[0].typeOfMachine").value("SCHUURMACHINE"))
//                .andExpect(jsonPath("$[1].id").value(stock2.getId().toString()))
                .andExpect(jsonPath("$[1].brandName").value("Haikoki"))
                .andExpect(jsonPath("$[1].productName").value("Metabo zaagmachine"))
                .andExpect(jsonPath("$[1].productNumber").value(302010))
                .andExpect(jsonPath("$[1].productInStock").value(100))
                .andExpect(jsonPath("$[1].orderPlacedDate").value("2019-10-30"))
                .andExpect(jsonPath("$[1].weeksToDelivery").value(12))
                .andExpect(jsonPath("$[1].productSold").value(2)) // Hier invullen wat juist is
                .andExpect(jsonPath("$[1].quantityInStock").value(0)) // Hier invullen wat juist is
                .andExpect(jsonPath("$[1].outOfStock").value(true)) // Hier invullen wat juist is
                .andExpect(jsonPath("$[1].typeOfMachine").value("ZAAGMACHINE"));



    }

    @Test
    @Order(2)
    void getOneStock() throws Exception{

       mockMvc.perform(get("/stocks/" + stock2.getId().toString()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("id").value(stock2.getId()))
               .andExpect(jsonPath("brandName").value("Haikoki"));
    }

    @Test
    @Order(5)
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

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andReturn();


        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();
        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/stocks/" + createdId));


    }


    @Test
    @Order(3)
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
                "id" : 1,
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

        String stockIdUpdate = "7";
        mockMvc.perform(put("/stocks/" + stockIdUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value("MetaboSuper"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Metabo SCHROEFMACHINE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(123456));
    }




    @Test
    @Order(4)
    void deleteStocks() throws Exception{
        mockMvc.perform(delete("/stocks/8"))
    .andExpect(status().isNoContent());
    }


}