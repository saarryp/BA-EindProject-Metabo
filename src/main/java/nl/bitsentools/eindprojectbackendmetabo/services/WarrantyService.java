package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.transaction.Transactional;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final ProductRepository productRepository;

    public WarrantyService(WarrantyRepository warrantyRepository, ProductRepository productRepository){
        this.warrantyRepository = warrantyRepository;
        this.productRepository = productRepository;
    }


//    GET-ALLwarranties
    public List<WarrantyOutputDto> getAllWarranties(){
        List<WarrantyModel>warrantyModelList = warrantyRepository.findAll();
        List<WarrantyOutputDto>warrantyOutputDtoList = new ArrayList<>();
        for(WarrantyModel warranty : warrantyModelList) {
            warrantyOutputDtoList.add(transferToDto(warranty));
        }
        return warrantyOutputDtoList;
    }

//    GetbyID

    public WarrantyOutputDto getOneWarrantyById(Long id){
        Optional<WarrantyModel> warrantyModelOptional = warrantyRepository.findById(id);
        if (warrantyModelOptional.isPresent()){
            return transferToDto(warrantyModelOptional.get());
        } else {
            throw new RecordNotFoundException("Warranty with id : " + id + "is not found");
        }
    }


    //post

    @Transactional
    public WarrantyOutputDto createWarranty(WarrantyInputDto createWarrantyDto) {
        WarrantyModel warrantyModel = new WarrantyModel();
        WarrantyModel warranty = transferToWarranty(warrantyModel, createWarrantyDto);

        ProductModel product = productRepository.findById(createWarrantyDto.getProductModelId())
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + createWarrantyDto.getProductModelId()));

        warranty.setProductModel(product);

        WarrantyModel warrantyModel1 = warrantyRepository.save(warranty);
        return transferToDto(warrantyModel1);

    }

    //put

    @Transactional
    public WarrantyOutputDto updateWarranty(Long id, WarrantyInputDto warrantyInputDto){
        WarrantyModel existingWarranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This Warranty with id :" + id + "is not found."));

        transferToWarranty(existingWarranty, warrantyInputDto);

        existingWarranty.setProductModel(productRepository.findById(warrantyInputDto.getProductModelId())
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + warrantyInputDto.getProductModelId())));


    WarrantyModel updatedWarranty = warrantyRepository.save(existingWarranty);
    return transferToDto(existingWarranty);

    }

    //delete

public void deleteWarranty(Long id) {
        try {
            warrantyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Warranty with id :" + id + "is not found.");
        }
}


    public WarrantyModel transferToWarranty(WarrantyModel warrantyModel, WarrantyInputDto dto) {
        warrantyModel.setWarrantyStart(dto.getWarrantyStart());
        warrantyModel.setWarrantyEnds(dto.getWarrantyEnds());

        ProductModel productModel = productRepository.findById(dto.getProductModelId())
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + dto.getProductModelId()));

        warrantyModel.setProductModel(productModel);

        return warrantyModel;
    }


    public WarrantyOutputDto transferToDto(WarrantyModel warrantyModel){
        WarrantyOutputDto dto = new WarrantyOutputDto();

        dto.setId(warrantyModel.getId());
        dto.setWarrantyStart(warrantyModel.getWarrantyStart());
        dto.setWarrantyEnds(warrantyModel.getWarrantyEnds());

        if (warrantyModel.getProductModel() != null) {
            dto.setProductModelId(warrantyModel.getProductModel().getId());
        }

        return dto;
    }
}
