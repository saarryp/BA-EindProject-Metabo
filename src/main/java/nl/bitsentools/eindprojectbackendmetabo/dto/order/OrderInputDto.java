package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.

    @NotNull(message = "Bestelnummer is verplicht")
    public Integer orderNumber;

    @NotNull(message = "prijs is verplicht")
    public Double price;

    @Min(value = 1, message = "er moet minimaal 1 prodct worden besteld.")
    public Integer quantity;

    @NotNull(message = "Gebruiker ID is verplicht")
    private Long userId;

    @NotNull(message = "Productnummer is verplicht")
    private Long productNumber;
    public InvoiceModel invoiceModel;

    public OrderModel orderModel;

    public OrderInputDto(){}

    public OrderInputDto(Integer orderNumber, Double price, Integer quantity, Long userId, Long productNumber, InvoiceModel invoiceModel, OrderModel orderModel) {
        this.orderNumber = orderNumber;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
        this.productNumber = productNumber;
        this.orderModel = orderModel;
        this.invoiceModel = invoiceModel;
    }
//    public OrderInputDto( int orderNumber, int quantity,
//                         double price, InvoiceModel invoiceModel) {
//        this.orderNumber = orderNumber;
//        this.quantity = quantity;
//        this.price = price;
//        this.invoiceModel = invoiceModel;
//    }

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


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }
}
