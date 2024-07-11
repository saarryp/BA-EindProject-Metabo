package nl.bitsentools.eindprojectbackendmetabo.services;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {


    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockService stockservice;

    //want service wordt geinjecteerd met de mock van erboven
    StockModel stock;


    @BeforeEach
    void setUp() {

        stock = new StockModel(
                101L,
                2,
                20,
                15,
                false);
 }

        @AfterEach
        void tearDown () {
            stock = null;
        }

        @Test
        @DisplayName("Should get all products that are in stock.")
        void getAllStocks () {

            //ARRANGE

            when(stockRepository.findAll()).thenReturn(List.of(stock));

            //ACT

            List<StockOutputDto> result = stockservice.getAllStocks();

            //ASSERT

            StockInputDto stockInputDto = new StockInputDto();
            stockInputDto.setWeeksToDelivery(stock.getWeeksToDelivery());
            stockInputDto.setProductSold(stock.getProductSold());
            stockInputDto.setQuantityInStock(stock.getQuantityInStock());
            stockInputDto.setOutOfStock(stock.isOutOfStock());
        }

    @Test
    @DisplayName("Should get one stock product by id, if stock exists")
    void getOneStockById() {

        //ARRANGE

        when(stockRepository.findById(101L)).thenReturn(Optional.of(stock));

        //ACT

        StockOutputDto resultbyId = stockservice.getOneStockById(101L);

        //ASSERT
        assertNotNull(resultbyId);
        assertEquals(101L, resultbyId.getId());;;;;
        assertEquals(2, resultbyId.getWeeksToDelivery());
        assertEquals(20, resultbyId.getProductSold());
        assertEquals(15, resultbyId.getQuantityInStock());
        assertFalse(resultbyId.isOutOfStock());;
    }

    @Test
    @DisplayName("should throw RecordNotFoundsException if stock doesn't exist")
    void getOneStockById_StockNonExistent(){

        //ARRANGE

        Long id = 101L;
        when(stockRepository.findById(id)).thenReturn(Optional.empty());

        //ACT

        //ASSERT

        assertThrows(RecordNotFoundException.class, () -> stockservice.getOneStockById(id));

    }

    @Test
    @DisplayName("Should post/create a new product to the stock")
    void createStock() {

        //ARRANGE


        StockInputDto stockInputDto = new StockInputDto();
        stockInputDto.setWeeksToDelivery(stock.getWeeksToDelivery());
        stockInputDto.setProductSold(stock.getProductSold());
        stockInputDto.setQuantityInStock(stock.getQuantityInStock());;

        StockModel savedStock = new StockModel();
        savedStock.setId(102L);;
        savedStock.setWeeksToDelivery(1);
        savedStock.setProductSold(5);
        savedStock.setQuantityInStock(15);
        savedStock.setOutOfStock(false);;

        when(stockRepository.save(any(StockModel.class))).thenReturn(savedStock);

        //ACT

        StockOutputDto createResult = stockservice.createStock(stockInputDto);

        //ASSERT
        assertNotNull(createResult);
        assertEquals(102, createResult.getId());
        assertEquals(1, createResult.getWeeksToDelivery());
        assertEquals(5, createResult.getProductSold());
        assertEquals(15, createResult.getQuantityInStock());;
    }

    @Test
    @DisplayName("should update stock")
    void updateStock() {

        //ARRANGE

        Long stockId = stock.getId();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));


        StockInputDto stockInputDto = new StockInputDto();
        stockInputDto.setWeeksToDelivery(3);
        stockInputDto.setProductSold(25);
        stockInputDto.setQuantityInStock(10);
        stockInputDto.setOutOfStock(false);

        StockModel updatedStock = new StockModel(
                stockId,
                3,
                25,
                10,
                false
        );

        // Mock de save methode
        when(stockRepository.save(any(StockModel.class))).thenReturn(updatedStock);


        //ACT

       StockOutputDto updatedResult = stockservice.updateStock(stockId, stockInputDto);


        //ASSERT

        assertNotNull(updatedResult);
        assertEquals(stockId, updatedResult.getId());
        assertEquals(3, updatedResult.getWeeksToDelivery());
        assertEquals(25, updatedResult.getProductSold());
        assertEquals(10, updatedResult.getQuantityInStock());

    }

        @Test
        @DisplayName("Should delete a product that is in stock")
        void deleteStock () {

            //ARRANGE


            //ACT

            stockservice.deleteStock(101L);

            //ASSERT

            verify(stockRepository, Mockito.times(1)).deleteById(101L);

        }

        @Test
        @DisplayName("should throw RecordNotFoundException when stock is not found")
        void deleteStock_StockNotFound(){
        //ARRANGE

            Long id = 101L;
            doThrow(EmptyResultDataAccessException.class).when(stockRepository).deleteById(id);

        //ACT

        //ASSERT
            assertThrows(RecordNotFoundException.class, () -> stockservice.deleteStock(id));

    }


        @Test
        @DisplayName("Should transfer data from StockInputDto to StockModel")
        void transferToStock() {
        //ARRANGE

        StockInputDto inputDto = new StockInputDto();
        inputDto.setWeeksToDelivery(2);
        inputDto.setProductSold(100);
        inputDto.setQuantityInStock(400);
        inputDto.setOutOfStock(true);



        //ACT

        StockModel updatedStock = stockservice.transferToStock(stock, inputDto);

        //ASSERT

        assertNotNull(updatedStock);;
        assertEquals(2, updatedStock.getWeeksToDelivery());
        assertEquals(100, updatedStock.getProductSold());
        assertEquals(400, updatedStock.getQuantityInStock());
        assertTrue(updatedStock.isOutOfStock());

    }

}
