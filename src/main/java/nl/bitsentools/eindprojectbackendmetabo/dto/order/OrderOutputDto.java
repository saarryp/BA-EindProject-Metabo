package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import nl.bitsentools.eindprojectbackendmetabo.dto.invoice.InvoiceOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;

import java.util.List;

public class OrderOutputDto {

    public Long id;
    public Long userId;
    public int orderNumber;
    public List<Object> productDto;

    public double price;
    public int quantity;
    public double totalPriceOrder;
//    public long invoiceModel;
    private InvoiceOutputDto invoiceModel;

    public OrderOutputDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Object> getProductDto() {
        return productDto;
    }

    public void setProductDto(List<Object> productDto) {
        this.productDto = productDto;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPriceOrder() {
        return totalPriceOrder;
    }

    public void setTotalPriceOrder(double totalPriceOrder) {
        this.totalPriceOrder = totalPriceOrder;
    }


    public InvoiceOutputDto getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceOutputDto invoiceModel) {
        this.invoiceModel = invoiceModel;
    }
}
