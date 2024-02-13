package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.StockRepository;
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
        StockModel stock = transferToStock(createStockDto);
        stockRepository.save(stock);
        return transferToDto(stock);
    }



    //Put-createStock

    //DeleteStockbyId

    //twee methodes aanmaken om inputDto naar model om te zetten
    //en model naar outputDto

    public StockModel transferToStock(StockInputDto dto){
        var stock = new StockModel();


        stock.setBrandName(dto.brandName);
        stock.setProductName(dto.productName);
        stock.setProductNumber(dto.productNumber);
        stock.setProductInStock(dto.productInStock);
        stock.setOrderPlacedDate(dto.orderPlacedDate);
        stock.setWeeksToDelivery(dto.weeksToDelivery);
        stock.setProductSold(dto.productSold);
        stock.setQuantityInStock(dto.quantityInStock);
        stock.setOutOfStock(dto.outOfStock);
        stock.setTypeOfMachine(dto.typeOfMachine);

        return stock;
    }

    public StockOutputDto transferToDto(StockModel stockModel){
        StockOutputDto dto = new StockOutputDto();

        //deze omzetten naar product

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
