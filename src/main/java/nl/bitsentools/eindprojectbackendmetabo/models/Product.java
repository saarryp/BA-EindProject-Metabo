package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

@Entity
@Table(name = "product")



public class Product {

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

    public  Product(){}


    public Product( String brandName, String productName, int productNumber, double price, TypeOfMachine typeOfMachine) {
        this.brandName = brandName;
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.typeOfMachine = typeOfMachine;

        System.out.println("Product instance created: " + this);

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

    public TypeOfMachine getTypeOfMachine() {
        return typeOfMachine;
    }

    public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }

    }

