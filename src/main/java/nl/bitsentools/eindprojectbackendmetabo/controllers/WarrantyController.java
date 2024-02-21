package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.WarrantyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    private final WarrantyService warrantyService;
    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    //Get-all

    @GetMapping
    public ResponseEntity<List<WarrantyOutputDto>>getAllWarranties(){
    List<WarrantyOutputDto>warranties = warrantyService.getAllWarranties();

        return ResponseEntity.ok(warranties);
    }

    //get-byId

    @GetMapping("{id}")
    public ResponseEntity<WarrantyOutputDto>getOneWarranty(@PathVariable("id")Long id){
        WarrantyOutputDto warrantyOutputDto = warrantyService.getOneWarrantyById(id);
        return ResponseEntity.ok(warrantyOutputDto);
    }

    //post

    @PostMapping
    public ResponseEntity<WarrantyOutputDto>createWarranty(@RequestBody WarrantyInputDto warrantyInputDto){
        WarrantyOutputDto savedWarranty = warrantyService.createWarranty(warrantyInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedWarranty.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedWarranty);
    }

    //put

    @PutMapping("{id}")
    public ResponseEntity<WarrantyOutputDto>updateWarranty(@PathVariable("id") Long id, @Valid @RequestBody WarrantyInputDto updateWarranty){
        WarrantyOutputDto dto = warrantyService.updateWarranty(id, updateWarranty);
        return ResponseEntity.ok().body(dto);
    }

    //delete
}
