package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
