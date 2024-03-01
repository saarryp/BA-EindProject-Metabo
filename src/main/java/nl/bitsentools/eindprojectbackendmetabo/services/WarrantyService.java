package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public WarrantyService(WarrantyRepository warrantyRepository){
        this.warrantyRepository = warrantyRepository;
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

    public WarrantyOutputDto createWarranty(WarrantyInputDto createWarrantyDto) {
        WarrantyModel warrantyModel = new WarrantyModel();
        WarrantyModel warranty = transferToWarranty(warrantyModel, createWarrantyDto);
        warrantyRepository.save(warranty);
        return transferToDto(warranty);

    }

    //put

    public WarrantyOutputDto updateWarranty(Long id, WarrantyInputDto warrantyInputDto){
        WarrantyModel existingWarranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This Warranty with id :" + id + "is not found."));
    transferToWarranty(existingWarranty, warrantyInputDto);

    warrantyRepository.save(existingWarranty);
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

    //twee methodes van dto naar warranty en waarrany naar model

    public WarrantyModel transferToWarranty(WarrantyModel warrantyModel, WarrantyInputDto dto) {
        warrantyModel.setProductName(dto.getProductName());
        warrantyModel.setWarrantyStart(dto.getWarrantyStart());
        warrantyModel.setWarrantyEnds(dto.getWarrantyEnds());
        return warrantyModel;
    }


    public WarrantyOutputDto transferToDto(WarrantyModel warrantyModel){
        WarrantyOutputDto dto = new WarrantyOutputDto();

        dto.setId(warrantyModel.getId());
        dto.setProductName(warrantyModel.getProductName());
        dto.setWarrantyStart(warrantyModel.getWarrantyStart());
        dto.setWarrantyEnds(warrantyModel.getWarrantyEnds());
        return dto;
    }
}
