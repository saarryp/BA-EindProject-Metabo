package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import jakarta.persistence.Column;
import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceInputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.

//    @NotNull(message = "Bestelnummer is verplicht")
    public Integer orderNumber;

    @NotNull(message = "prijs is verplicht")
    public Double price;

    @Min(value = 1, message = "er moet minimaal 1 prodct worden besteld.")
    public Integer quantity;

    @NotNull(message = "Gebruiker ID is verplicht")
    private Long userId;

    @Column(unique = true)
    @NotNull(message = "Productnummer is verplicht")
    private Integer productNumber;
//    public InvoiceModel invoiceModel;

//    private List<Long> productIds;

    public InvoiceInputDto invoiceModel;
    public OrderModel orderModel;

    public OrderInputDto(){}

    public OrderInputDto(Integer orderNumber, Double price, Integer quantity, Long userId, Integer productNumber, List<Long> productIds, InvoiceInputDto invoiceModel, OrderModel orderModel) {
        this.orderNumber = orderNumber;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
        this.productNumber = productNumber;
//        this.productIds = productIds;
        this.orderModel = orderModel;
        this.invoiceModel = invoiceModel;
    }

    private static final int MINIMUM_NUMBER_OF_PRODUCTS = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < MINIMUM_NUMBER_OF_PRODUCTS) {
            throw new IllegalArgumentException("Aantal orders kan niet onder minimum van 1 liggen");
        }
        this.quantity = quantity;
    }


    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

//    public List<Long> getProductIds() {
//        return productIds;
//    }
//
//    public void setProductIds(List<Long> productIds) {
//        this.productIds = productIds;
//    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public InvoiceInputDto getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceInputDto invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    //    public InvoiceModel getInvoiceModel() {
//        return invoiceModel;
//    }
//
//    public void setInvoiceModel(InvoiceModel invoiceModel) {
//        this.invoiceModel = invoiceModel;
//    }
//
 }
