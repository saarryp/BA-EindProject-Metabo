package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //GetAllStocks
    public List<StockOutputDto>getAllStocks(){
        List<StockModel>stockModelList = stockRepository.findAll();
        List<StockOutputDto>stockOutputDtoList = new ArrayList<>();
        for(StockModel stock : stockModelList) {
            stockOutputDtoList.add(transferToDto(stock));
        }
        return stockOutputDtoList;
    }

    //GetStockById


    public StockOutputDto getOneStockById(Long id){
        Optional<StockModel> stockModelOptional = stockRepository.findById(id);
        if (stockModelOptional.isPresent()) {
            return transferToDto(stockModelOptional.get());
        } else {
            throw new RecordNotFoundException("product with id :" + id + " not found");
        }
    }


    //Post-addStock
    public StockOutputDto createStock(StockInputDto createStockDto) {
        StockModel stockModel = new StockModel();
        StockModel stock = transferToStock(stockModel, createStockDto);
        StockModel stockmodel1 = stockRepository.save(stock);
        return transferToDto(stockmodel1);
    }

    //Put-createStock

    public StockOutputDto updateStock(Long id, StockInputDto stockDto) {
        StockModel existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Stockproduct with id: " + id + " not found"));

        transferToStock(existingStock, stockDto);

        StockModel updatedStock = stockRepository.save(existingStock);
        return transferToDto(updatedStock);
    }


    //DeleteStockbyId


    public void deleteStock(Long id) {
        try {
            stockRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new RecordNotFoundException("Stockproduct with id: " + id + "not found");
        }
    }


    public StockModel transferToStock(StockModel stock,StockInputDto dto){


        stock.setBrandName(dto.getBrandName());
        stock.setProductName(dto.getProductName());
        stock.setProductNumber(dto.getProductNumber());
        stock.setProductInStock(dto.getProductInStock());
        stock.setOrderPlacedDate(dto.getOrderPlacedDate());
        stock.setWeeksToDelivery(dto.getWeeksToDelivery());
        stock.setProductSold(dto.getProductSold());
        stock.setQuantityInStock(dto.getQuantityInStock());
        stock.setOutOfStock(dto.isOutOfStock());
        stock.setTypeOfMachine(dto.getTypeOfMachine());

        return stock;
    }

    public StockOutputDto transferToDto(StockModel stockModel){
        StockOutputDto dto = new StockOutputDto();


        dto.setId(stockModel.getId());
        dto.setBrandName(stockModel.getBrandName());
        dto.setProductName(stockModel.getProductName());
        dto.setProductNumber(stockModel.getProductNumber());
        dto.setProductInStock(stockModel.getProductInStock());
        dto.setOrderPlacedDate(stockModel.getOrderPlacedDate());
        dto.setWeeksToDelivery(stockModel.getWeeksToDelivery());
        dto.setProductSold(stockModel.getProductSold());
        dto.setQuantityInStock(stockModel.getQuantityInStock());
        dto.setOutOfStock(stockModel.isOutOfStock());
        dto.setTypeOfMachine(stockModel.getTypeOfMachine());


        return dto;
    }

}
