package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
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


    //GET-ALLwarranties
    public List<WarrantyOutputDto> getAllWarranties(){
        List<WarrantyModel>warrantyModelList = warrantyRepository.findAll();
        List<WarrantyOutputDto>warrantyOutputDtoList = new ArrayList<>();
        for(WarrantyModel warranty : warrantyModelList) {
            warrantyOutputDtoList.add(transferToDto(warranty));
        }
        return warrantyOutputDtoList;
    }

    //GetbyID

    public WarrantyOutputDto getOneWarrantyById(Long id){
        Optional<WarrantyModel> warrantyModelOptional = warrantyRepository.findById(id);
        if (warrantyModelOptional.isPresent()){
            return transferToDto(warrantyModelOptional.get());
        } else {
            throw new RecordNotFoundException("Warranty with id : " + id + "is not found");
        }
    }


    //post

    //put

    //delete

    //twee methodes van dto naar warranty en waarranty naar model


    public WarrantyOutputDto transferToDto(WarrantyModel warrantyModel){
        WarrantyOutputDto dto = new WarrantyOutputDto();

        dto.setId(warrantyModel.getId());
        dto.setProductWarranty(warrantyModel.isProductWarranty());
        dto.setWarrantyInMoths(warrantyModel.getWarrantyInMoths());
        dto.setWarrantyStart(warrantyModel.getWarrantyStart());
        dto.setWarrantyEnds(warrantyModel.getWarrantyEnds());
        return dto;
    }
}
