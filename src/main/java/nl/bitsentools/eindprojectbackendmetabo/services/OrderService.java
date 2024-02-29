package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
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

import static nl.bitsentools.eindprojectbackendmetabo.mapper.ProductMapper.transferToProductDto;
import static nl.bitsentools.eindprojectbackendmetabo.mapper.ProductMapper.transferToProduct;


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
        Optional<OrderModel> orderModelOptional = orderRepository.findById(id);
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
        product.ifPresent(productModel -> existingOrder.getProductModel().add(productModel));
        existingOrder.setOrderNumber(dto.orderNumber);
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
        List<ProductOutputDto> products = new ArrayList<>();
        products.add(transferToProductDto(productRepository.findById(orderModel.getId()).get()));
        OrderOutputDto dto = new OrderOutputDto();
        dto.setId(orderModel.getId());
        dto.setUserId(orderModel.getUserId());
        dto.setUserEmail(orderModel.getUserEmail());
        dto.setUserDetails(orderModel.getUserDetails());
        dto.setOrderNumber(orderModel.getOrderNumber());
        dto.setProductDto(products);
        dto.setPrice(orderModel.getPrice());

        dto.setQuantity(orderModel.getQuantity());
        dto.setTotalPriceOrder((orderModel.getTotalPriceOrder()));

        return dto;
    }

    //AssignOrderToProduct voor many-to-many relatie
    public void assignOrderToProduct(Long orderId, Long productId) {
        var optionalOrder = orderRepository.findById(orderId);
        var optionalProduct = productRepository.findById(productId);
        if(optionalOrder.isPresent() && optionalProduct.isPresent()){
            var order = optionalOrder.get();
            var product = optionalProduct.get();

            order.getProductModel().add(product);
            product.getOrderModel().add(order);
            orderRepository.save(order);
            productRepository.save(product);

        } else {
            throw new RecordNotFoundException("Order or product not found.");
        }
    }

    public void assignAllOrdersToAllProducts(){
        List<OrderModel> allOrders = orderRepository.findAll();
        List<ProductModel> allProducts = productRepository.findAll();

        for (OrderModel order : allOrders){
            for (ProductModel product : allProducts){
                order.getProductModel().add(product);
                product.getOrderModel().add(order);
                orderRepository.save(order);
                productRepository.save(product);
            }
        }
    }
}
