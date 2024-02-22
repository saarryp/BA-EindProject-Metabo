package nl.bitsentools.eindprojectbackendmetabo.dto.order;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.
    //nog even aan het bomen of de ADmin hier wel een andere rol in krijgt.
    public String userEmail;
    public String userDetails;
    public int orderNumber;

    public String productName;

    public int productNumber;
    public double price;
    public int quantity;

    public double totalPriceOrder;

    public OrderInputDto(){}

    public String getUserEmail() {
        return userEmail;
    }

    public OrderInputDto(String userEmail, String userDetails, int orderNumber, String productName, int quantity, int productNumber, double price, double totalPriceOrder) {
        this.userEmail = userEmail;
        this.userDetails = userDetails;
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.productNumber = productNumber;
        this.price = price;
        this.totalPriceOrder = totalPriceOrder;
    }

//    public OrderInputDto(String userEmail, String userDetails, int quantity, int productNumber) {
//        this.userEmail = userEmail;
//        this.userDetails = userDetails;
//        this.quantity = quantity;
//        this.productNumber = productNumber;
//    }

    private static final int MINIMUM_NUMBER_OF_PRODUCTS = 1;

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

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPriceOrder() {
        return totalPriceOrder;
    }

    public void setTotalPriceOrder(double totalPriceOrder) {
        this.totalPriceOrder = totalPriceOrder;
    }
}
