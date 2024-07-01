package nl.bitsentools.eindprojectbackendmetabo.services;

import jakarta.transaction.Transactional;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.order.OrderOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDtoWarranty;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.mapper.InvoiceMapper;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
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

import java.time.LocalDate;
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
public OrderOutputDto createOrder(OrderInputDto createOrderDto) {
    // Haal de gebruikersinformatie op vanuit de beveiligingscontext
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    UserModel user = userRepository.findUserModelByUsername(username)
            .orElseThrow(() -> new RecordNotFoundException("Gebruiker niet gevonden voor gebruikersnaam: " + username));


    //controleer of het product bestaat
    ProductModel product = productRepository.findByProductNumber(createOrderDto.getProductNumber())
            .orElseThrow(() -> new RecordNotFoundException("Product met id: " + createOrderDto.getProductNumber() + " niet gevonden."));

    //maak een nieuwe order aan
    OrderModel orderModel = transferToOrder(createOrderDto);
    orderModel.setUser(user);
    orderModel.getProductModel().add(product);


    // Koppel de gebruiker aan de bestelling


//    // Als er een InvoiceModel is meegegeven, haal deze dan op uit de database en koppel het aan de order
//    if (createOrderDto.getInvoiceModel() != null) {
//        InvoiceModel invoice = invoiceRepository.findById(createOrderDto.getInvoiceModel().getId())
//                .orElseThrow(() -> new RecordNotFoundException("Factuur niet gevonden voor id: " + createOrderDto.getInvoiceModel().getId()));
//        orderModel.setInvoiceModel(invoice);
//    }
    // Als er een InvoiceModel is meegegeven, maak deze dan aan en koppel het aan de order
//    if (createOrderDto.getInvoiceModel() != null) {
//        InvoiceModel invoiceModel = new InvoiceModel();
//        invoiceModel.setInvoiceId(createOrderDto.getInvoiceModel().getInvoiceNumber());
//        invoiceModel.setInvoiceDate(createOrderDto.getInvoiceModel().getInvoiceDate());
//        invoiceModel.setTotalAmount(createOrderDto.getInvoiceModel().getTotalAmount());
//
//        invoiceModel = invoiceRepository.save(invoiceModel); // Sla de factuur op in de database
//        orderModel.setInvoiceModel(invoiceModel); // Koppel de factuur aan de order
//    }
    // Als er een InvoiceModel is meegegeven, maak deze dan aan en koppel het aan de order
    if (createOrderDto.getInvoiceModel() != null) {
        InvoiceModel invoiceModel = InvoiceMapper.transferToInvoiceModel(createOrderDto.getInvoiceModel());
        invoiceModel = invoiceRepository.save(invoiceModel); // Sla de factuur op in de database
        orderModel.setInvoiceModel(invoiceModel); // Koppel de factuur aan de order
    }


    //opslaan van de order
    OrderModel savedOrderModel = orderRepository.save(orderModel);

    return transferToDto(savedOrderModel);
}

    //PutById for CLIENT
public OrderOutputDto updateOrder(Long id, OrderInputDto updateDto) {

        OrderModel existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order with id: " + id + "is not found."));

        existingOrder.setQuantity(updateDto.quantity);
    existingOrder.setTotalPriceOrder(updateDto.getPrice() * updateDto.getQuantity());
//    existingOrder.setInvoiceModel(updateDto.getInvoiceModel());


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
        existingOrder.setTotalPriceOrder(updateDto.getPrice() * updateDto.getQuantity());
//        existingOrder.setInvoiceModel(updateDto.getInvoiceModel());

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


    public OrderModel transferToOrder(OrderInputDto dto) {

        var existingOrder = new OrderModel();

//        ProductModel product = productRepository.findByProductNumber(dto.getProductNumber())
//                .orElseThrow(() -> new RecordNotFoundException("Product with productnumber: " + dto.getProductNumber() + " not found."));

        //LIJST ipv model, maar dat lukt niet want krijg error bij verandren naar List
        Optional<ProductModel> products = productRepository.findByProductNumber(dto.getProductNumber());
        if (products.isEmpty()) {
            throw new RecordNotFoundException("Product with product number: " + dto.getProductNumber() + " not found.");
        }
        ProductModel product = products.get();
        existingOrder.setOrderNumber(dto.getOrderNumber());
        existingOrder.setPrice(dto.price);
        existingOrder.setQuantity(dto.quantity);
        existingOrder.setTotalPriceOrder(dto.getPrice() * dto.getQuantity());
        existingOrder.getProductModel().add(product);

        //iets aanroepen van de invoice met de juiste gegevens

        // Als er een InvoiceModel is meegegeven, maak deze dan aan en koppel het aan de order
        InvoiceModel invoiceModel = null;
        if (dto.getInvoiceModel() != null) {
            invoiceModel = InvoiceMapper.transferToInvoiceModel(dto.getInvoiceModel());
            existingOrder.setInvoiceModel(invoiceModel);
        }
        //service create invoice
        InvoiceInputDto invoiceInputDto = new InvoiceInputDto();
       invoiceInputDto.setDateOfPurchase(LocalDate.now());



        //for
        invoiceInputDto.setNetPriceWithoutVat(product.getPrice());

//DEZE NOG AFMAKEN!!!
//        InvoiceModel i = new InvoiceModel();
//        i.setVatRate();
//        existingOrder.setInvoiceModel(i);

//        existingOrder.setInvoiceModel(dto.invoiceModel);

        return existingOrder;
    }

    public OrderOutputDto transferToDto(OrderModel orderModel){
        List<Object> products = new ArrayList<>();

        OrderOutputDto dto = new OrderOutputDto();
        dto.setId(orderModel.getId());
        dto.setOrderNumber(orderModel.getOrderNumber());
        dto.setPrice(orderModel.getPrice());
        dto.setQuantity(orderModel.getQuantity());
        dto.setTotalPriceOrder(orderModel.getTotalPriceOrder());


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

        if(orderModel.getUser() !=null){
            dto.setUserId(orderModel.getUser().getId());
        }


        dto.setProductDto(products);

//        dto.setInvoiceModel(orderModel.getInvoiceModel().getId());

        return dto;
    }

    @Transactional
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

    @Transactional
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
    @Transactional
    public void assignUserToOrder(Long orderId, Long userId) {
        var optionalOrder = orderRepository.findById(orderId);
        var optionalUser = userRepository.findById(userId);

        if (optionalOrder.isPresent() && optionalUser.isPresent()) {
            var order = optionalOrder.get();
            var user = optionalUser.get();


            order.setUser(user);
            orderRepository.save(order);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
