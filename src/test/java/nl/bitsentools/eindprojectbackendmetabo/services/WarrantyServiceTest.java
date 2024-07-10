package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.parameters.P;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarrantyServiceTest {

  @Mock
    WarrantyRepository warrantyRepository;
  @Mock
  ProductRepository productRepository;

    @InjectMocks
    WarrantyService warrantyService;
    WarrantyModel warrantyWithProduct = new WarrantyModel();

    @BeforeEach
    void setUp(){
        //Met garantie
        warrantyWithProduct = new WarrantyModel();
        warrantyWithProduct.setId(101L);
        warrantyWithProduct.setWarrantyStart(LocalDate.of(2024, 4, 24));
        warrantyWithProduct.setWarrantyEnds(LocalDate.of(2026,4, 24));

        //koppeling factuur aan garantie


        ProductModel product = new ProductModel();
        warrantyWithProduct.setProductModel(new ProductModel());
    }

    @AfterEach
    void tearDown () {
        warrantyWithProduct = null;
    }

    @Test
    @DisplayName("should get all warranties")
    void getAllWarranties() {

        //ARRANGE

        when(warrantyRepository.findAll()).thenReturn(List.of(warrantyWithProduct));

        //ACT

        List<WarrantyOutputDto>result = warrantyService.getAllWarranties();

        //ASSERT

        WarrantyInputDto warrantyInputDto = new WarrantyInputDto();
        warrantyInputDto.setWarrantyStart(warrantyWithProduct.getWarrantyStart());
        warrantyInputDto.setWarrantyEnds(warrantyWithProduct.getWarrantyEnds());
    }

    @Test
    @DisplayName("Should get one warranty by id, if warranty exists")
    void getOneWarrantyById() {

        //ARRANGE

        when(warrantyRepository.findById(101L)).thenReturn(Optional.of(warrantyWithProduct));

        //ACT
         WarrantyOutputDto resultById = warrantyService.getOneWarrantyById(101L);


        //ASSERT

        assertEquals(101L, resultById.getId());
        assertEquals(LocalDate.of(2024, 4, 24), resultById.getWarrantyStart());
        assertEquals(LocalDate.of(2026,4,24), resultById.getWarrantyEnds());
    }

    @Test
    @DisplayName("should throw RecordNotFoundException if warranty doesnt exist")
    void getOneWarrantyById_WarrantyNonExistent(){

        //ARRANGE

        Long id = 101L;
        when(warrantyRepository.findById(id)).thenReturn(Optional.empty());

        //ACT

        //ASSERT

        assertThrows(RecordNotFoundException.class, () -> warrantyService.getOneWarrantyById(id));
    }

    @Test
    @DisplayName("Should post/create a warranty to a product")
    void createWarranty() {

        //ARRANGE

        WarrantyInputDto warrantyInputDto = new WarrantyInputDto();
        warrantyInputDto.setWarrantyStart(LocalDate.of(2024, 1, 15));
        warrantyInputDto.setWarrantyEnds(LocalDate.of(2026, 1, 15));
        warrantyInputDto.setProductModelId(1l);
        WarrantyModel savedWarranty = new WarrantyModel();
        savedWarranty.setId(102L);
        savedWarranty.setWarrantyStart(LocalDate.of(2024,1, 15));
        savedWarranty.setWarrantyEnds(LocalDate.of(2026, 1, 15));

        ProductModel productModel = new ProductModel();
        productModel.setId(1l);

        savedWarranty.setProductModel(productModel);
        productRepository.save(productModel);
        when(productRepository.findById(1l)).thenReturn(Optional.of(productModel));
        when(warrantyRepository.save(ArgumentMatchers.any(WarrantyModel.class))).thenReturn(savedWarranty);


        //ACT

        WarrantyOutputDto createResult = warrantyService.createWarranty(warrantyInputDto);

        //ASSERT

        assertNotNull(createResult);
        assertEquals(102, createResult.getId());
        assertEquals(LocalDate.of(2024, 1, 15), createResult.getWarrantyStart());
        assertEquals(LocalDate.of(2026, 1, 15), createResult.getWarrantyEnds());

    }

    @Test
    @DisplayName("should update warranty")
    void updateWarranty() {

        //ARRANGE

        WarrantyInputDto updatedWarrantyInputDto = new WarrantyInputDto();
        updatedWarrantyInputDto.setWarrantyStart((warrantyWithProduct.getWarrantyStart()));
        updatedWarrantyInputDto.setWarrantyEnds(warrantyWithProduct.getWarrantyEnds());
        updatedWarrantyInputDto.setProductModelId(1L);

        Long warrantyId = 101L;

        ProductModel productModel = new ProductModel();
        productModel.setId(1l);



        when(warrantyRepository.findById(warrantyId)).thenReturn(Optional.of(warrantyWithProduct));
        when(productRepository.findById(1L)).thenReturn(Optional.of(productModel));
        when(warrantyRepository.save(any(WarrantyModel.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //ACT

        WarrantyOutputDto updatedResult = warrantyService.updateWarranty(warrantyId, updatedWarrantyInputDto);

        //ASSERT

        assertNotNull(updatedResult);
        assertEquals(warrantyWithProduct.getWarrantyStart(), updatedResult.getWarrantyStart());
        assertEquals(warrantyWithProduct.getWarrantyEnds(), updatedResult.getWarrantyEnds());
        assertEquals(updatedWarrantyInputDto.getProductModelId(), updatedResult.getProductModelId());

    }

    @Test
    @DisplayName("Should delete one warranty if warranty exists")
    void deleteWarranty() {

        //ARRANGE

        //ACT

        warrantyService.deleteWarranty(101L);

        //ASSERT

        verify(warrantyRepository, Mockito.times(1)).deleteById(101L);
    }

    @Test
    @DisplayName("should throw RecordNotFoundException when warranty doesn't exist")
    void deleteWarranty_WarrantyNonExistent(){

        //ARRANGE

        Long id = 101L;
        doThrow(EmptyResultDataAccessException.class).when(warrantyRepository).deleteById(id);

        //ACT

       // ASSERT

        assertThrows(RecordNotFoundException.class, () -> warrantyService.deleteWarranty(id));
    }


    @Test
    @DisplayName("should transfer WarrantyInputDto to WarrantyModel")
    void transferToWarranty() {

        //ARRANGE

        WarrantyInputDto inputDto = new WarrantyInputDto();
        inputDto.setWarrantyStart(LocalDate.of(2024, 3, 30));
        inputDto.setWarrantyEnds(LocalDate.of(2028, 3,30));
        inputDto.setProductModelId(1L);

        // Simuleer het ProductModel in de repository
        ProductModel productModel = new ProductModel();
        productModel.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(productModel));



        //ACT

        WarrantyModel updatedWarranty = warrantyService.transferToWarranty(warrantyWithProduct, inputDto);

        //ASSERT

        assertNotNull(updatedWarranty);
        assertEquals(LocalDate.of(2024,3,30), updatedWarranty.getWarrantyStart());
        assertEquals(LocalDate.of(2028, 3, 30), updatedWarranty.getWarrantyEnds());
        assertEquals(1L, updatedWarranty.getProductModel().getId());
    }

    @Test
    @DisplayName("should transfer warrantyModel to WarrantyOutputDto")
    void transferToDto() {
        //ARRANGE

            WarrantyModel warrantyModel = new WarrantyModel();
            warrantyModel.setId(105L);
            warrantyModel.setWarrantyStart(LocalDate.of(2022, 12, 15));
            warrantyModel.setWarrantyEnds(LocalDate.of(2026, 12, 15));

        // Simuleer het ProductModel in WarrantyModel
        ProductModel productModel = new ProductModel();
        productModel.setId(1L);
        warrantyModel.setProductModel(productModel);

        //ACT

        WarrantyOutputDto dto = warrantyService.transferToDto(warrantyModel);

        //ASSERT

        assertNotNull(dto);
        assertEquals(105L, dto.getId());
        assertEquals(LocalDate.of(2022, 12, 15), dto.getWarrantyStart());
        assertEquals(LocalDate.of(2026, 12, 15), dto.getWarrantyEnds());
        assertEquals(1L, dto.getProductModelId());
    }
}