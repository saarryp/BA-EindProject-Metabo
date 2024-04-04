package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.persistence.Entity;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

//TODO: LET OP EXCEPTIONS BIJ TESTEN

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockService stockservice;

    //want service wordt geinjecteerd met de mock van erboven


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should get all products that are in stock.")
    void getAllStocks() {

        //ARRANGE
        StockModel stock = new StockModel();

        stock.setId(101L);
        stock.setBrandName("Metabo");
        stock.setProductName("Metabo Zaagmachine 12345");
        stock.setProductNumber(1001);
        stock.setProductInStock(15000);

        LocalDate orderPlacedDate = LocalDate.of(2024, 4, 4);
        stock.setOrderPlacedDate(orderPlacedDate);

        stock.setQuantityInStock(15);
        stock.setTypeOfMachine(TypeOfMachine.ZAAGMACHINE);

        when(stockRepository.findAll()).thenReturn(List.of(stock));

        //ACT

        List<StockOutputDto> result = stockservice.getAllStocks();

        //ASSERT

        assertEquals(101L, result.get(0).getId());
        assertEquals("Metabo", result.get(0).getBrandName());
        assertEquals("Metabo Zaagmachine 12345", result.get(0).getProductName());
        assertEquals(1001, result.get(0).getProductNumber());
        assertEquals(15000, result.get(0).getProductInStock());
        assertEquals(orderPlacedDate, result.get(0).getOrderPlacedDate());
        assertEquals(15, result.get(0).getQuantityInStock());
        assertEquals(TypeOfMachine.ZAAGMACHINE, result.get(0).getTypeOfMachine());
    }

    @Test
    void getOneStockById() {

        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    void createStock() {
        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    void updateStock() {

        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    @DisplayName("Should delete a product that is in stock")
    void deleteStock() {

        //ARRANGE



        StockModel stock = new StockModel();

        stock.setId(101L);
        stock.setBrandName("Metabo");
        stock.setProductName("Metabo Zaagmachine 12345");
        stock.setProductNumber(1001);
        stock.setProductInStock(15000);

        LocalDate orderPlacedDate = LocalDate.of(2024, 4, 4);
        stock.setOrderPlacedDate(orderPlacedDate);

        stock.setQuantityInStock(15);
        stock.setTypeOfMachine(TypeOfMachine.ZAAGMACHINE);

        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));


        //ACT

        stockservice.deleteStock(101L);
        StockOutputDto result = stockservice.getOneStockById(1L);

        //ASSERT
//        assertEquals(0L, result.id);
        verify(stockRepository, Mockito.times(1)).deleteById(101L);

    }

    @Test
    void transferToStock() {
        //ARRANGE

        //ACT

        //ASSERT

    }

    @Test
    void transferToDto() {

        //ARRANGE

        //ACT

        //ASSERT
    }
}