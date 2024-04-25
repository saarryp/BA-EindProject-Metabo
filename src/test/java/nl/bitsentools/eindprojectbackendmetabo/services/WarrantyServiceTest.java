package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarrantyServiceTest {

  @Mock
    WarrantyRepository warrantyRepository;

    @InjectMocks
    WarrantyService warrantyService;

    WarrantyModel warrantyWithInvoice = new WarrantyModel();

    @BeforeEach
    void setUp(){

        //Met garantie

                warrantyWithInvoice.setId(101L);
                warrantyWithInvoice.setProductNumber(1001);
                warrantyWithInvoice.setWarrantyStart(LocalDate.of(2024, 4, 24));
                warrantyWithInvoice.setWarrantyEnds(LocalDate.of(2026,4, 24));


                //koppeling factuur aan garantie

        InvoiceModel invoice = new InvoiceModel();
        warrantyWithInvoice.setInvoiceModel(invoice);

    }
    @AfterEach
    void tearDown () {
        warrantyWithInvoice = null;
    }

    @Test
    @DisplayName("should get all warranties")
    void getAllWarranties() {

        //ARRANGE

        when(warrantyRepository.findAll()).thenReturn(List.of(warrantyWithInvoice));

        //ACT

        List<WarrantyOutputDto>result = warrantyService.getAllWarranties();

        //ASSERT

        WarrantyInputDto warrantyInputDto = new WarrantyInputDto();
        warrantyInputDto.setProductNumber(warrantyWithInvoice.getProductNumber());
        warrantyInputDto.setWarrantyStart(warrantyWithInvoice.getWarrantyStart());
        warrantyInputDto.setWarrantyEnds(warrantyWithInvoice.getWarrantyEnds());
    }

    @Test
    @DisplayName("Should get one warranty by id")
    void getOneWarrantyById() {

        //ARRANGE

        when(warrantyRepository.findById(101L)).thenReturn(Optional.of(warrantyWithInvoice));

        //ACT
         WarrantyOutputDto resultById = warrantyService.getOneWarrantyById(101L);


        //ASSERT

        assertEquals(101L, resultById.getId());
        assertEquals(1001, resultById.getProductNumber());
        assertEquals(LocalDate.of(2024, 4, 24), resultById.getWarrantyStart());
        assertEquals(LocalDate.of(2026,4,24), resultById.getWarrantyEnds());
    }

    @Test
    @DisplayName("Should post/create a warranty to an invoice")
    void createWarranty() {

        //ARRANGE

        WarrantyInputDto warrantyInputDto = new WarrantyInputDto();
        warrantyInputDto.setProductNumber(1001);
        warrantyInputDto.setWarrantyStart(LocalDate.of(2024, 1, 15));
        warrantyInputDto.setWarrantyEnds(LocalDate.of(2026, 1, 15));

        WarrantyModel savedWarranty = new WarrantyModel();
        savedWarranty.setId(102L);
        savedWarranty.setProductNumber(1002);
        savedWarranty.setWarrantyStart(LocalDate.of(2024,1, 15));
        savedWarranty.setWarrantyEnds(LocalDate.of(2026, 1, 15));

        when(warrantyRepository.save(ArgumentMatchers.any(WarrantyModel.class))).thenReturn(savedWarranty);


        //ACT

        WarrantyOutputDto createResult = warrantyService.createWarranty(warrantyInputDto);

        //ASSERT

        assertNotNull(createResult);
        assertEquals(102, createResult.getId());
        assertEquals(1002, createResult.getProductNumber());
        assertEquals(LocalDate.of(2024, 1, 15), createResult.getWarrantyStart());
        assertEquals(LocalDate.of(2026, 1, 15), createResult.getWarrantyEnds());

    }

    @Test
    @DisplayName("should update warranty")
    void updateWarranty() {

        //ARRANGE

        //test if warranty changed from productnumber 1001 to 1020
        WarrantyInputDto updatedWarrantyInputDto = new WarrantyInputDto();
        updatedWarrantyInputDto.setProductNumber(1020);
        updatedWarrantyInputDto.setWarrantyStart((warrantyWithInvoice.getWarrantyStart()));
        updatedWarrantyInputDto.setWarrantyEnds(warrantyWithInvoice.getWarrantyEnds());

        when(warrantyRepository.findById(101L)).thenReturn(Optional.of(warrantyWithInvoice));

        //ACT

        WarrantyOutputDto updatedResult = warrantyService.updateWarranty(101L, updatedWarrantyInputDto);

        //ASSERT

        assertNotNull(updatedResult);
        assertEquals(warrantyWithInvoice.getProductNumber(), updatedResult.getProductNumber());
        assertEquals(warrantyWithInvoice.getWarrantyStart(), updatedResult.getWarrantyStart());
        assertEquals(warrantyWithInvoice.getWarrantyEnds(), updatedResult.getWarrantyEnds());

    }

    @Test
    @DisplayName("Should delete one warranty")
    void deleteWarranty() {

        //ARRANGE

        //ACT

        warrantyService.deleteWarranty(101L);

        //ASSERT

        verify(warrantyRepository, Mockito.times(1)).deleteById(101L);
    }

    @Test
    @DisplayName("should update warranty")
    void transferToWarranty() {

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