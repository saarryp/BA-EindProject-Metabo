package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.WarrantyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //post

    //put

    //delete
}
