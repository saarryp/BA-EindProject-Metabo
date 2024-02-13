package nl.bitsentools.eindprojectbackendmetabo.controllers;



import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<StockOutputDto>> getAllStocks(){
        List<StockOutputDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    //GetById

    @GetMapping("{id}")
    public ResponseEntity<StockOutputDto>getOneStock(@PathVariable("id")Long id){
        StockOutputDto stockOutputDto = stockService.getOneStockById(id);
        return ResponseEntity.ok(stockOutputDto);
    }

    //Post-create
    @PostMapping
    public ResponseEntity<StockOutputDto> createStock(@RequestBody StockInputDto stockInputDto) {

        StockOutputDto savedStock = stockService.createStock(stockInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedStock.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedStock);

    }


    //Put-updateById

    //Delete-byId
}
