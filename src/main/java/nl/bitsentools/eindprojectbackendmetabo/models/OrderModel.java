package nl.bitsentools.eindprojectbackendmetabo.models;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")

public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column

    private Long id;
    @Column
    private int orderNumber;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
    private double totalPriceOrder;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<ProductModel> productModel  = new ArrayList<>();

    @OneToOne(mappedBy = "orderModel", cascade = CascadeType.ALL)
    @NotNull
    InvoiceModel invoiceModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_user_id")
    private UserModel user;

    public OrderModel(){}

    public OrderModel(Long id,  int orderNumber,  double price, int quantity, double totalPriceOrder, ProductModel productModel,InvoiceModel invoiceModel, UserModel user) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.price = price;
        this.quantity = quantity;
        this.totalPriceOrder = totalPriceOrder;
        this.productModel = new ArrayList<>();
        this.invoiceModel = invoiceModel;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
