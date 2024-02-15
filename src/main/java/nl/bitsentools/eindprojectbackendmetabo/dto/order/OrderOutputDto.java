package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import jakarta.persistence.Column;

public class OrderOutputDto {

    public Long id;

public int userId;

public String userEmail;

public String userDetails;

public int orderNumber;

public int productName;

public int productNumber;

public double price;
public int numberOfProducts;

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

    public int getProductName() {

    return productName;
    }

    public void setProductName(int productName) {

    this.productName = productName;
    }

    public int getProductNumber() {

    return productNumber;
    }

    public void setProductNumber(int productNumber) {

    this.productNumber = productNumber;
    }

    public double getPrice() {

    return price;
    }

    public void setPrice(double price) {

    this.price = price;
    }

    public int getNumberOfProducts() {
        
    return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {

    this.numberOfProducts = numberOfProducts;
    }


}
