package nl.bitsentools.eindprojectbackendmetabo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
public class ProductController {
    @GetMapping("")
    public String getAllProducts(){
        return "halloooooootjes";
    }
}

