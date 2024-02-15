package nl.bitsentools.eindprojectbackendmetabo.dto.order;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.
    //nog even aan het bomen of de ADmin hier wel een andere rol in krijgt.
    public String userEmail;
    public String userDetails;

    public int numberOfProducts;

    public int productNumber;

    public String getUserEmail() {
        return userEmail;
    }


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

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        if (numberOfProducts < MINIMUM_NUMBER_OF_PRODUCTS) {
            throw new IllegalArgumentException("Aantal orders kan niet onder minimum van 1 liggen");
        }
        this.numberOfProducts = numberOfProducts;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }



}
