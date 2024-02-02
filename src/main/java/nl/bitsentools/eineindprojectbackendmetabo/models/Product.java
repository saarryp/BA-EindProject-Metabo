package nl.bitsentools.eineindprojectbackendmetabo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")


public class Product {



    private Long id;
    private String brandName;
    private String productName;
    private int productNumber;
    private double price;

    @Enumerated(EnumType.STRING)
    private String typeOfMachine;


    public Product(Long id, String brandName, String productName, int productNumber, double price, String typeOfMachine, String typeOfAccessory) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.typeOfMachine = typeOfMachine;

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

    public int setProductNumber(int productNumber) {
      return productNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypeOfMachine() {
        return typeOfMachine;
    }

    public void setTypeOfMachine(String typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }

    }

