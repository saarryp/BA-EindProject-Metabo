package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")

public class ProductModel {

@Id
@GeneratedValue
@Column

private Long id;
@Column
    private String brandName;
@Column
    private String productName;
@Column
    private int productNumber;
@Column
    private double price;

@Column
@Enumerated(EnumType.STRING)
    private TypeOfMachine typeOfMachine;

@Column
    private boolean productWarranty;
@Column
    private int warrantyInMonths;


    @ManyToMany(mappedBy = "productModel")
    List< OrderModel> orderModel = new ArrayList<>();

    @OneToMany(mappedBy = "productModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageData> imageData = new ArrayList<>();

    @OneToOne(mappedBy = "productModel", cascade = CascadeType.ALL)
    WarrantyModel warrantyModel;
    //    @OneToOne( cascade = CascadeType.ALL)
//    @JoinColumn(name = "warranty_id")
//    WarrantyModel warrantyModel;


    public ProductModel(){}


    public ProductModel(Long id, String brandName, String productName, int productNumber, double price, TypeOfMachine typeOfMachine, boolean productWarranty, int warrantyInMonths, List<OrderModel> orderModel, List<ImageData>imageData, WarrantyModel warrantyModel) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.typeOfMachine = typeOfMachine;
        this.productWarranty = productWarranty;
        this.warrantyInMonths = warrantyInMonths;
        this.orderModel = orderModel;
        this.imageData = imageData;
        this.warrantyModel = warrantyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public void  setProductNumber(int productNumber) {
      this.productNumber = productNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypeOfMachine getTypeOfMachine() {
        return typeOfMachine;
    }

    public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }

    public boolean isProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(boolean productWarranty) {
        this.productWarranty = productWarranty;
    }

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
    }

    public List<OrderModel> getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(List<OrderModel> orderModel) {
        this.orderModel = orderModel;
    }

    public List<ImageData> getImageData() {
        return imageData;
    }

    public void setImageData(List<ImageData> imageData) {
        this.imageData = imageData;
    }

    public WarrantyModel getWarrantyModel() {
        return warrantyModel;
    }

    public void setWarrantyModel(WarrantyModel warrantyModel) {
        this.warrantyModel = warrantyModel;
    }
}

