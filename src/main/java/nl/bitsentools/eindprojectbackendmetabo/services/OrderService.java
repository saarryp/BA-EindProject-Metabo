package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
        OrderModel orderModel = transferToOrder(createOrderDto);
        orderRepository.save(orderModel);
        return transferToDto(orderModel);
    }

    //PutById

    //deleteById

    //twee methodes voor orderInputDto naar orderModel

    public OrderModel transferToOrder(OrderInputDto dto){
        var order = new OrderModel();

//
        order.setOrderNumber(dto.orderNumber);
        order.setProductName(dto.productName);
        order.setPrice(dto.price);

        order.setUserEmail(dto.userEmail);
        order.setUserDetails(dto.userDetails);
        order.setNumberOfProducts(dto.numberOfProducts);
        order.setProductNumber(dto.productNumber);
        return order;
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
        dto.setNumberOfProducts(orderModel.getNumberOfProducts());

        return dto;
    }
}
