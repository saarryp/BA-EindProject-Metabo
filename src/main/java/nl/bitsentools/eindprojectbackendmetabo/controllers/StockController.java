package nl.bitsentools.eindprojectbackendmetabo.controllers;


import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

//getall

    @GetMapping
    public ResponseEntity<List><StockOutputDto>> getAllStocks(){
        List<StockOutputDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    //GetById

    //Post-create

    //Put-updateById

    //Delete-byId
}
