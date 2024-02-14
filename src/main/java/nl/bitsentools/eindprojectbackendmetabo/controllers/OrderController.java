package nl.bitsentools.eindprojectbackendmetabo.controllers;


import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOutputDto>>getAllOrders() {
        List<ProductOutputDto> orders = OrderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }


}
