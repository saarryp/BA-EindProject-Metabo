package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockOutputDto>getAllStocks(){
        List<StockModel>stockModelList = stockRepository.findAll();
        List<StockOutputDto>stockOutputDtoList = new ArrayList<>();
        for(StockModel m : stockModelList) {
            stockModelList.add(nog in te vullen method)//kijken of door naamswijziging de Product nog wel werkt. en edez ook nalopen. nog twee methodes aankaen zpdat deze omgetzet kunnen worden.
        }
    }
}
