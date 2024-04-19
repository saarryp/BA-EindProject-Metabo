package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.inject.Inject;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getAllWarranties() {
    }

    @Test
    void getOneWarrantyById() {
    }

    @Test
    void createWarranty() {
    }

    @Test
    void updateWarranty() {
    }

    @Test
    void deleteWarranty() {
    }

    @Test
    void transferToWarranty() {
    }

    @Test
    void transferToDto() {
    }
}