package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;

@Entity
@Table(name = "order")

public class OrderModel {

    @Id
    @GeneratedValue
    @Column

    private Long id;

    @Column
    //foreignkey
    private int userId;
    @Column
    private String userEmail;
    @Column
    private String userDetails;
    @Column
    private int orderNumber;
    @Column
    private int productName;
    @Column
    private int productNumber;
    @Column
    private double price;
    @Column
    private int numberOfProducts;

    public OrderModel(){}

    public OrderModel(Long id, int userId, String userEmail, String userDetails, int orderNumber, int productName, int productNumber, double price, int numberOfProducts) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userDetails = userDetails;
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.numberOfProducts = numberOfProducts;
    }

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
