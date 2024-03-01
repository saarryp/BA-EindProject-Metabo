package nl.bitsentools.eindprojectbackendmetabo.models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")

public class OrderModel {

    @Id
    @GeneratedValue
    @Column

    private Long id;


//productnam en nummer niet nodig hier TODO
    //INVOICE ONE TO ONE MAKEN
    //INVOICE GARANTIE FACTUUR ONE TO ONE MAKEN
    //USERDETAIL NA MKEN USER KUNNEN VEEL WEGGEHAALD WORDEN ZODAT NIET DUBBELOP IS TODO
    @Column
    private int userId;
    @Column
    private String userEmail;
    @Column
    private String userDetails;
    @Column
    private int orderNumber;
    @Column
    private String productName;
    @Column
    private int productNumber;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
    private double totalPriceOrder;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))


    List<ProductModel> productModel  = new ArrayList<>();

    public OrderModel(){}

    public OrderModel(Long id, int userId, String userEmail, String userDetails, int orderNumber, String productName, ProductModel productModel, int productNumber, double price, int quantity, double totalPriceOrder) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userDetails = userDetails;
        this.orderNumber = orderNumber;
        this.productName = productName;

        this.productNumber = productNumber;
        this.price = price;
        this.quantity = quantity;
        this.totalPriceOrder = totalPriceOrder;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
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

    public List<ProductModel> getProductModel() {
        return productModel;
    }

    public void setProductModel(List<ProductModel> productModel) {
        this.productModel = productModel;
    }
}
//TODO: ORDER EN INVOICE IS OOK EEN ONE TO ONE RELATIE DIE GEMAAKT MOET WORDEN.
//TODO: