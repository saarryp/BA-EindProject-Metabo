package nl.bitsentools.eindprojectbackendmetabo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.junit.jupiter.api.*;
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

    StockModel stock1;
    StockModel stock2;

   @BeforeEach
    public void setUp(){
       stock1 = new StockModel(1L,
               10,
               16, 10, false);

       stock2 = new StockModel(2L,
               100,
               12, 2, true);


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
                .andExpect(jsonPath("$[0].weeksToDelivery").value(16))
                .andExpect(jsonPath("$[0].productSold").value(0))
                .andExpect(jsonPath("$[0].quantityInStock").value(10))
                .andExpect(jsonPath("$[0].outOfStock").value(false))

                .andExpect(jsonPath("$[1].weeksToDelivery").value(12))
                .andExpect(jsonPath("$[1].productSold").value(2))
                .andExpect(jsonPath("$[1].quantityInStock").value(0))
                .andExpect(jsonPath("$[1].outOfStock").value(true));

    }

    @Test
    @Order(2)
    void getOneStock() throws Exception{

       mockMvc.perform(get("/stocks/" + stock2.getId().toString()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("id").value(stock2.getId()))
               .andExpect(jsonPath("weeksToDelivery").value(12))
               .andExpect(jsonPath("productSold").value(2))
               .andExpect(jsonPath("quantityInStock").value(0))
               .andExpect(jsonPath("outOfStock").value(true));
    }

    @Test
    @Order(5)
    void createStock() throws Exception {
        String jsonInput = """
                {
                 
                    "weeksToDelivery": 16,
                    "productSold": 0,
                    "quantityInStock": 10,
                    "outOfStock: false
                    
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
                "weeksToDelivery": 16,
                "productSold: 0,
                "quantityInStock": 10,
                "outOfStock": false
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
                "weeksToDelivery": 1,
                "productSold"; 0,
                "quantityInStock": 1,
                "outOfStock": false
               
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