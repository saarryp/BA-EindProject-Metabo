package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.OrderRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    //GET-all
    public List<OrderOutputDto> getAllOrders() {
        List<OrderModel> orderList = orderRepository.findAll();
        List<OrderOutputDto> orderOutputDtosList = new ArrayList<>();
        for(OrderModel orderModel : orderList){
            orderOutputDtosList.add(transferToDto(orderModel));
        }
        return orderOutputDtosList;
    }

    //GET-ById

    public OrderOutputDto getOneOrderById(Long id) {
        Optional<OrderModel>orderModelOptional = orderRepository.findById(id);
        if(orderModelOptional.isPresent()){
            return transferToDto(orderModelOptional.get());
        } else {
            throw new RecordNotFoundException("Order with id :" + id + "is not found.");
        }
    }

    //POST

    public OrderOutputDto createOrder(OrderInputDto createOrderDto){
        OrderModel order = new OrderModel();
        OrderModel orderModel = transferToOrder(createOrderDto);

        orderRepository.save(orderModel);
        return transferToDto(orderModel);
    }




    //PutById
public OrderOutputDto updateOrder(Long id, OrderInputDto updateDto) {
        OrderModel existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order with id: " + id + "is not found."));

//   transferToOrder(existingOrder, updateDto);
//   orderRepository.save(existingOrder);
//   return transferToDto(existingOrder);

//    existingOrder.setOrderNumber(updateDto.getOrderNumber());
//    existingOrder.setProductName(updateDto.getProductName());
//    existingOrder.setPrice(updateDto.getPrice());
//    existingOrder.setUserEmail(updateDto.getUserEmail());
//    existingOrder.setUserDetails(updateDto.getUserDetails());
//    existingOrder.setQuantity(updateDto.getQuantity());
//    existingOrder.setProductNumber(updateDto.getProductNumber());
    orderRepository.save(existingOrder);

    return transferToDto(existingOrder);

}
    //deleteById
@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
    try {
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    } catch (EmptyResultDataAccessException ex) {
        throw new RecordNotFoundException("Order with id: " + id + " not found");
    }
}

    //twee methodes voor orderInputDto naar orderModel

    public OrderModel transferToOrder(  OrderInputDto dto){

       var existingOrder = new OrderModel();
        var product = productRepository.findById(dto.productNumber);
//
        existingOrder.setOrderNumber(dto.orderNumber);
        existingOrder.setProductName(product.get().getProductName());
        existingOrder.setPrice(dto.price);

        existingOrder.setUserEmail(dto.userEmail);
        existingOrder.setUserDetails(dto.userDetails);
        existingOrder.setQuantity(dto.quantity);
        existingOrder.setProductNumber(dto.productNumber.intValue());
        existingOrder.setTotalPriceOrder(dto.getPrice() * dto.getQuantity());
        return existingOrder;
    }
    //van orderModel naar orderOutputDto

    public OrderOutputDto transferToDto(OrderModel orderModel){

        OrderOutputDto dto = new OrderOutputDto();
        dto.setId(orderModel.getId());
        dto.setUserId(orderModel.getUserId());
        dto.setUserEmail(orderModel.getUserEmail());
        dto.setUserDetails(orderModel.getUserDetails());
        dto.setOrderNumber(orderModel.getOrderNumber());
        dto.setProductName(orderModel.getProductName());
        dto.setPrice(orderModel.getPrice());
        dto.setProductNumber(orderModel.getProductNumber());
        dto.setQuantity(orderModel.getQuantity());
        dto.setTotalPriceOrder((orderModel.getTotalPriceOrder()));

        return dto;
    }

    //AssignOrderToProduct voor many-to-many relatie
    public void assignOrderToProduct(Long id, Long productId) {
        var optionalOrder = orderRepository.findById(id);
        var optionalProduct = productRepository.findById(productId);
        if(optionalOrder.isPresent() && optionalProduct.isPresent()){
            var order = optionalOrder.get();
            var product = optionalProduct.get();

            order.setProductModel(product);
            orderRepository.save(order);

        } else {
            throw new RecordNotFoundException("Order or product not found.");
        }
    }
}
