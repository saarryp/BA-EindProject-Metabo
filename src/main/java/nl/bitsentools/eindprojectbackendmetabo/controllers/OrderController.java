package nl.bitsentools.eindprojectbackendmetabo.controllers;


import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

  private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDto>>getAllOrders() {
        List<OrderOutputDto> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderOutputDto>getOneOrder(@PathVariable("id") Long id){
        OrderOutputDto orderOutputDto = orderService.getOneOrderById(id);

        return ResponseEntity.ok(orderOutputDto);
    }

    @PostMapping
    public ResponseEntity<OrderOutputDto>createOrder(@RequestBody OrderInputDto orderInputDto) {

       OrderOutputDto savedOrder = orderService.createOrder(orderInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedOrder.id)
                        .toUriString());
        return ResponseEntity.created(uri).body(savedOrder);
    }
}
