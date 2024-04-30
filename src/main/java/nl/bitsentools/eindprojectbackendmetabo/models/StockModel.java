package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

import java.time.LocalDate;

@Entity
@Table(name = "stocks")
public class StockModel {

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
    @Column @Enumerated(EnumType.STRING)
    private TypeOfMachine typeOfMachine;
    @Column
    private int productInStock;
    @Column
    private LocalDate orderPlacedDate;

    @Column
    private int weeksToDelivery;

    @Column
    private int productSold;
    @Column
    private int quantityInStock;
    @Column
    private boolean outOfStock;



    public StockModel(Long id, String brandName, String productName, int productNumber, int productInStock, LocalDate orderPlacedDate, int weeksToDelivery, int productSold, int quantityInStock, boolean outOfStock, TypeOfMachine typeOfMachine) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.productNumber = productNumber;
        this.productInStock = productInStock;
        this.orderPlacedDate = orderPlacedDate;
        this.weeksToDelivery = weeksToDelivery;
        this.productSold = productSold;
        this.quantityInStock = quantityInStock;
        this.outOfStock = outOfStock;
        this.typeOfMachine = typeOfMachine;
    }

    public StockModel() {

    }

//    public Long getId(long l) {
//        return id;
//    }
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

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }

    public LocalDate getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate(LocalDate orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    public int getWeeksToDelivery() {
        return weeksToDelivery;
    }

    public void setWeeksToDelivery(int weeksToDelivery) {
        this.weeksToDelivery = weeksToDelivery;
    }

    public int getProductSold() {
        return productSold;
    }

    public void setProductSold(int productSold) {
        this.productSold = productSold;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public TypeOfMachine getTypeOfMachine() {
        return typeOfMachine;
    }

    public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }
}
