package nl.bitsentools.eindprojectbackendmetabo.controllers;
import nl.bitsentools.eindprojectbackendmetabo.dto.id.IdInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

  private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


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
    public ResponseEntity<OrderOutputDto>createOrder(@Valid @RequestBody OrderInputDto orderInputDto) {

       OrderOutputDto savedOrder = orderService.createOrder(orderInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedOrder.id)
                        .toUriString());
        return ResponseEntity.created(uri).body(savedOrder);
    }

    @PostMapping("/orders/{id}/products")
    public ResponseEntity<Object>assignOrderToProduct(
            @PathVariable("id") Long id,
            @Valid @RequestBody IdInputDto input){
        orderService.assignOrderToProduct(id, input.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignAllOrders")
    public ResponseEntity<Object>assignAllOrdersToAllProducts(){
        orderService.assignAllOrdersToAllProducts();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignUserToOrder")
    public void assignUserToOrder(@RequestParam Long id, @RequestParam Long userId) {
        orderService.assignUserToOrder(id, userId);
    }

    @PostMapping("/{id}/invoices")
    public ResponseEntity<Object>assignOrderToInvoice(@PathVariable("id")Long id, @Valid @RequestBody IdInputDto input){
        orderService.assignOrderToInvoice(id, input.id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/client")
    public ResponseEntity<OrderOutputDto>updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderInputDto updateOrder){
    OrderOutputDto dto = orderService.updateOrder(id, updateOrder);
    return ResponseEntity.ok().body(dto);
    }


    @PutMapping("/{id}/admin")
    public ResponseEntity<OrderOutputDto> updateOrderForAdmin(@PathVariable("id") Long id, @Valid @RequestBody OrderInputDto updateDto) {
        OrderOutputDto updatedOrder = orderService.updateOrderForAdmin(id, updateDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
