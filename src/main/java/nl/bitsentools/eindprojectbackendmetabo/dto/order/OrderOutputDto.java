package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderOutputDto {

    public Long id;

    @NotNull(message = "Gebruikers-Id is verplicht")
    public int userId;

    @NotBlank(message = "E-mailadres van gebruiker is verplicht")
    public String userEmail;

    @NotBlank(message = "Leveringsadres van gebruiker is verplicht")
    public String userDetails;

    @NotNull(message = "Bestelnummer is verplicht")
    public int orderNumber;

    public List<Object> productDto;

    @NotNull(message = "Prijs is verplicht")
    public double price;

    @Min(value = 1, message = "Aantal producten van minimum 1 is verplicht")
    public int quantity;

    @NotNull(message = "Totaalprijs van alle bestelde prodcten is verplicht")
    public double totalPriceOrder;
    public InvoiceModel invoiceModel;





    public OrderOutputDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
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

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }
}
