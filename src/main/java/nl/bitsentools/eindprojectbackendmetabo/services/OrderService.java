package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //POST

    //PutById

    //deleteById

    //twee methodes voor orderInputDto naar orderModel
    //van orderModel naar orderOutputDto
}
