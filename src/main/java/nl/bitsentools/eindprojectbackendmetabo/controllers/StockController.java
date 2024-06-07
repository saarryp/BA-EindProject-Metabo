package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.stock.StockOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

      //getAll

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
    public ResponseEntity<StockOutputDto> createStock(@Valid @RequestBody StockInputDto stockInputDto) {

        StockOutputDto savedStock = stockService.createStock(stockInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedStock.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedStock);

    }
    //Put-updateById

    @PutMapping("/{id}")
    public ResponseEntity<StockOutputDto>updateStock(@PathVariable("id") Long id, @Valid @RequestBody StockInputDto updateStock ){

        StockOutputDto dto = stockService.updateStock(id, updateStock);
        return ResponseEntity.ok().body(dto);
    }

    //Delete-byId

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteStock(@PathVariable("id")Long id){
        stockService.deleteStock(id);
    return ResponseEntity.noContent().build();
    }
}
