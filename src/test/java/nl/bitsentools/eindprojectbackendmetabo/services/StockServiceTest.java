package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.persistence.Entity;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
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
                "Metabo",
                "Metabo Zaagmachine 12345",
                1001,
                15000,
                LocalDate.of(2024, 4, 4),
                1,
                20,
                15,
                false,
                TypeOfMachine.ZAAGMACHINE);
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

            assertEquals(101L, result.get(0).getId());
            assertEquals("Metabo", result.get(0).getBrandName());
            assertEquals("Metabo Zaagmachine 12345", result.get(0).getProductName());
            assertEquals(1001, result.get(0).getProductNumber());
            assertEquals(15000, result.get(0).getProductInStock());
            assertEquals(LocalDate.of(2024, 4, 4), result.get(0).getOrderPlacedDate());
            assertEquals(15, result.get(0).getQuantityInStock());
            assertEquals(TypeOfMachine.ZAAGMACHINE, result.get(0).getTypeOfMachine());
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
        assertEquals(101L, resultbyId.getId());
        assertEquals("Metabo", resultbyId.getBrandName());
        assertEquals("Metabo Zaagmachine 12345",resultbyId.getProductName());
        assertEquals(1001, resultbyId.getProductNumber());
        assertEquals(15000, resultbyId.getProductInStock());
        assertEquals(LocalDate.of(2024, 4, 4), stock.getOrderPlacedDate());
        assertEquals(1, resultbyId.getWeeksToDelivery());
        assertEquals(20, resultbyId.getProductSold());
        assertEquals(15, resultbyId.getQuantityInStock());
        assertFalse(resultbyId.isOutOfStock());
        assertEquals(TypeOfMachine.ZAAGMACHINE, resultbyId.getTypeOfMachine());

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
        stockInputDto.setBrandName(stock.getBrandName());
        stockInputDto.setProductName(stock.getProductName());
        stockInputDto.setProductNumber(stock.getProductNumber());
        stockInputDto.setProductInStock(stock.getProductInStock());
        stockInputDto.setOrderPlacedDate(stock.getOrderPlacedDate());
        stockInputDto.setQuantityInStock(stock.getQuantityInStock());
        stockInputDto.setTypeOfMachine(stock.getTypeOfMachine());

        StockModel savedStock = new StockModel();
        savedStock.setId(102L);
        savedStock.setBrandName("Metabo");
        savedStock.setProductName("Metabo Slijpmachine 12345");
        savedStock.setProductNumber(1001);
        savedStock.setProductInStock(5000);
        savedStock.setOrderPlacedDate(LocalDate.of(2024, 4, 4));
        savedStock.setWeeksToDelivery(1);
        savedStock.setProductSold(5);
        savedStock.setQuantityInStock(15);
        savedStock.setOutOfStock(false);
        savedStock.setTypeOfMachine(TypeOfMachine.SLIJPMACHINE);

        when(stockRepository.save(any(StockModel.class))).thenReturn(savedStock);

        //ACT

        StockOutputDto createResult = stockservice.createStock(stockInputDto);

        //ASSERT
        assertNotNull(createResult);
        assertEquals(102, createResult.getId());
        assertEquals("Metabo", createResult.getBrandName());
        assertEquals("Metabo Slijpmachine 12345", createResult.getProductName());
        assertEquals(1001, createResult.getProductNumber());
        assertEquals(5000, createResult.getProductInStock());
        assertEquals(LocalDate.of(2024, 4, 4), createResult.getOrderPlacedDate());
        assertEquals(1, createResult.getWeeksToDelivery());
        assertEquals(5, createResult.getProductSold());
        assertEquals(15, createResult.getQuantityInStock());
        assertEquals(TypeOfMachine.SLIJPMACHINE, createResult.getTypeOfMachine());

    }

    @Test
    @DisplayName("should update stock")
    void updateStock() {

        //ARRANGE

        //test if the updated stock from 15.000 is changed to 17.000 productInStock
        StockInputDto updatedStockInputDto = new StockInputDto();
        updatedStockInputDto.setBrandName(stock.getBrandName());
        updatedStockInputDto.setProductName(stock.getProductName());
        updatedStockInputDto.setProductNumber(stock.getProductNumber());
        updatedStockInputDto.setProductInStock(17000); // Updated productInStock value
        updatedStockInputDto.setOrderPlacedDate(stock.getOrderPlacedDate());
        updatedStockInputDto.setQuantityInStock(stock.getQuantityInStock());
        updatedStockInputDto.setTypeOfMachine(stock.getTypeOfMachine());

        when(stockRepository.findById(101L)).thenReturn(Optional.of(stock));

        //ACT

        StockOutputDto updatedResult = stockservice.updateStock(101L, updatedStockInputDto);

        //ASSERT

        assertNotNull(updatedResult);
        assertEquals(stock.getBrandName(), updatedResult.getBrandName());
        assertEquals(stock.getProductName(), updatedResult.getProductName());
        assertEquals(stock.getProductNumber(), updatedResult.getProductNumber());
        assertEquals(17000, updatedResult.getProductInStock()); // Check if productInStock is updated
        assertEquals(stock.getOrderPlacedDate(), updatedResult.getOrderPlacedDate());
        assertEquals(stock.getQuantityInStock(), updatedResult.getQuantityInStock());
        assertEquals(stock.getTypeOfMachine(), updatedResult.getTypeOfMachine());

        verify(stockRepository, times(1)).save(any(StockModel.class));

        // Check the updated amount in the repository
        Optional<StockModel> updatedStockOptional = stockRepository.findById(101L);
        assertTrue(updatedStockOptional.isPresent());
        StockModel updatedStock = updatedStockOptional.get();
        assertEquals(17000, updatedStock.getProductInStock());
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
        inputDto.setBrandName("Hikoki");
        inputDto.setProductName("Hikoki bitset 12345");
        inputDto.setProductNumber(1001);
        inputDto.setProductInStock(50);
        inputDto.setOrderPlacedDate(LocalDate.of(2024, 4, 20));
        inputDto.setWeeksToDelivery(2);
        inputDto.setProductSold(100);
        inputDto.setQuantityInStock(400);
        inputDto.setOutOfStock(true);
        inputDto.setTypeOfMachine(TypeOfMachine.BITSETS);


        //ACT

        StockModel updatedStock = stockservice.transferToStock(stock, inputDto);

        //ASSERT

        assertNotNull(updatedStock);
        assertEquals("Hikoki", updatedStock.getBrandName());
        assertEquals("Hikoki bitset 12345", updatedStock.getProductName());
        assertEquals(1001, updatedStock.getProductNumber());
        assertEquals(50, updatedStock.getProductInStock());
        assertEquals(LocalDate.of(2024, 4, 20), updatedStock.getOrderPlacedDate());
        assertEquals(2, updatedStock.getWeeksToDelivery());
        assertEquals(100, updatedStock.getProductSold());
        assertEquals(400, updatedStock.getQuantityInStock());
        assertTrue(updatedStock.isOutOfStock());
        assertEquals(TypeOfMachine.BITSETS, updatedStock.getTypeOfMachine());

    }

    @Test
    @DisplayName("should transfer StockModel to StockOutputDto")
    void transferToDto() {

        //ARRANGE


        //ACT

        StockOutputDto resultDto = stockservice.transferToDto(stock);

        //ASSERT

        assertNotNull(resultDto);
        assertEquals(101L, resultDto.getId());
        assertEquals("Metabo", resultDto.getBrandName());
        assertEquals("Metabo Zaagmachine 12345", resultDto.getProductName());
        assertEquals(1001, resultDto.getProductNumber());
        assertEquals(15000, resultDto.getProductInStock());
        assertEquals(LocalDate.of(2024, 4, 4), resultDto.getOrderPlacedDate());
        assertEquals(1, resultDto.getWeeksToDelivery());
        assertEquals(20, resultDto.getProductSold());
        assertEquals(15, resultDto.getQuantityInStock());
        assertFalse(resultDto.isOutOfStock());
        assertEquals(TypeOfMachine.ZAAGMACHINE, resultDto.getTypeOfMachine());
        }
    }
