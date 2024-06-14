package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.transaction.Transactional;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDtoWarranty;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.models.UserModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.InvoiceRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.OrderRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static nl.bitsentools.eindprojectbackendmetabo.services.ProductService.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static nl.bitsentools.eindprojectbackendmetabo.mapper.ProductMapper.transferToProductDto;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, InvoiceRepository invoiceRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
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
@Transactional
    public OrderOutputDto createOrder(OrderInputDto createOrderDto){
        // Haal de gebruikersinformatie op vanuit de beveiligingscontext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gebruik de gevonden gebruikersnaam om de gebruiker te vinden
        UserModel user = userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException("Gebruiker niet gevonden voor gebruikersnaam: " + username));


        OrderModel order = new OrderModel();
//        order.setUser(user);
        OrderModel orderModel = transferToOrder(createOrderDto, user);

        orderRepository.save(orderModel);
        return transferToDto(orderModel);
    }

    //PutById for CLIENT
public OrderOutputDto updateOrder(Long id, OrderInputDto updateDto) {

        OrderModel existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order with id: " + id + "is not found."));
        existingOrder.setUserEmail(updateDto.userEmail);
        existingOrder.setUserDetails(updateDto.userDetails);
        existingOrder.setQuantity(updateDto.quantity);

    orderRepository.save(existingOrder);

    return transferToDto(existingOrder);

}

// PUT-ID FOR ADMIN

    public OrderOutputDto updateOrderForAdmin(Long id, OrderInputDto updateDto) {
       OrderModel existingOrder = orderRepository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException("Order with id: " + id + "is not found."));
        existingOrder.setOrderNumber(updateDto.orderNumber);
        existingOrder.setPrice(updateDto.price);
        existingOrder.setQuantity((updateDto.quantity));
        existingOrder.setUserDetails(updateDto.userDetails);

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


    public OrderModel transferToOrder(OrderInputDto dto, UserModel user){

       var existingOrder = new OrderModel();

       if(dto.productNumber == null){
           throw new IllegalArgumentException("Product Id cannot be null");
       }

       var product = productRepository.findById(dto.productNumber);

        product.ifPresent(productModel -> existingOrder.getProductModel().add(productModel));
        existingOrder.setOrderNumber(dto.orderNumber);
        existingOrder.setPrice(dto.price);
        existingOrder.setUserEmail(dto.userEmail);
        existingOrder.setUserDetails(dto.userDetails);
        existingOrder.setQuantity(dto.quantity);
        existingOrder.setTotalPriceOrder(dto.getPrice() * dto.getQuantity());
        existingOrder.setInvoiceModel(dto.invoiceModel);


        return existingOrder;
    }

    public OrderOutputDto transferToDto(OrderModel orderModel){
        List<Object> products = new ArrayList<>();

        OrderOutputDto dto = new OrderOutputDto();
        dto.setId(orderModel.getId());
        dto.setUserId(orderModel.getUserId());
        dto.setUserEmail(orderModel.getUserEmail());
        dto.setUserDetails(orderModel.getUserDetails());
        dto.setOrderNumber(orderModel.getOrderNumber());
        dto.setPrice(orderModel.getPrice());
        dto.setQuantity(orderModel.getQuantity());
        dto.setTotalPriceOrder(orderModel.getTotalPriceOrder());
        dto.setInvoiceModel(orderModel.getInvoiceModel());

        for (ProductModel productModel : orderModel.getProductModel()){

            if (productModel.isProductWarranty()){
                ProductOutputDtoWarranty productOutputDtoWarranty = transferToDtoWarranty(productModel);
                products.add(productOutputDtoWarranty);
            }
            else {
                ProductOutputDto productOutPutDto = transferToProductDto(productModel);
                products.add(productOutPutDto);

            }
        }
        dto.setProductDto(products);

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
    @Transactional
    public void assignOrderToInvoice(Long orderId, Long invoiceId){
        var optionalOrder = orderRepository.findById(orderId);
        var optionalInvoice = invoiceRepository.findById(invoiceId);

        if(optionalOrder.isPresent() && optionalInvoice.isPresent()) {
            var order = optionalOrder.get();
            var invoice = optionalInvoice.get();

            order.setInvoiceModel(invoice);
            orderRepository.save(order);
            System.out.println("order assigned to invoice succesfull");
        } else {
            System.out.println("order or invoice not found");
            throw new RecordNotFoundException("Order or invoice not found. ");
        }
    }
}
