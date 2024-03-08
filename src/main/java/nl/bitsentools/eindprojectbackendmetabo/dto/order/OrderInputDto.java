package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.
    //nog even aan het bomen of de ADmin hier wel een andere rol in krijgt.
    public String userEmail;
    public String userDetails;
    public int orderNumber;

    public Long productNumber;
    public double price;
    public int quantity;
    public InvoiceModel invoiceModel;

    public OrderInputDto(){}


    public OrderInputDto(String userEmail, String userDetails, int orderNumber, int quantity,
                         Long productNumber, double price, InvoiceModel invoiceModel) {
        this.userEmail = userEmail;
        this.userDetails = userDetails;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.productNumber = productNumber;
        this.price = price;
        this.invoiceModel = invoiceModel;
    }

    private static final int MINIMUM_NUMBER_OF_PRODUCTS = 1;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < MINIMUM_NUMBER_OF_PRODUCTS) {
            throw new IllegalArgumentException("Aantal orders kan niet onder minimum van 1 liggen");
        }
        this.quantity = quantity;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
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

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }
}
