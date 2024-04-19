package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.inject.Inject;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarrantyServiceTest {

    @Mock
    WarrantyRepository warrantyRepository;

    @InjectMocks
    WarrantyService warrantyService;

    WarrantyModel warranty;

    @BeforeEach
    void setUp(){

        //Met garantie
       WarrantyModel warrantyWithInvoice = new WarrantyModel();
                warrantyWithInvoice.setId(1L);
                warrantyWithInvoice.setProductNumber(1001);
                warrantyWithInvoice.setWarrantyStart(LocalDate.of(2024, 4, 24));
                warrantyWithInvoice.setWarrantyEnds(LocalDate.of(2026,4, 24));


                //koppeling factuur aan garantie

        InvoiceModel invoice = new InvoiceModel();
        warrantyWithInvoice.setInvoiceModel(invoice);

    }
    @AfterEach
    void tearDown () {
        warranty = null;
    }

    @Test
    @DisplayName("should get all warranties")
    void getAllWarranties() {

        //ARRANGE

        when(warrantyRepository.findAll()).thenReturn(List.of(warranty));

        //ACT

        List<WarrantyOutputDto>result = warrantyService.getAllWarranties();

        //ASSERT

        WarrantyInputDto warrantyInputDto = new WarrantyInputDto();
        warrantyInputDto.setProductNumber(warranty.getProductNumber());
        warrantyInputDto.setWarrantyStart(warranty.getWarrantyStart());
        warrantyInputDto.setWarrantyEnds(warranty.getWarrantyEnds());
    }

    @Test
    void getOneWarrantyById() {

        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    void createWarranty() {

        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    void updateWarranty() {
        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
    void deleteWarranty() {
        //ARRANGE

        //ACT

        //ASSERT
    }

    @Test
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