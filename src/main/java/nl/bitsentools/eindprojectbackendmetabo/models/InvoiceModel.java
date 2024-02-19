package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")

public class InvoiceModel {
    @Id
    @GeneratedValue
    @Column

    private Long id;
    @Column
    private String invoiceId;
    @Column
    private int productNumber;
    @Column
    private String productName;
    @Column
    private double totalPrice;
    @Column
    private double vat21ProductPrice;
    @Column
    private double vat9ProductPrice;
    @Column
    private int userId;
    @Column
    private String userAddress;
    @Column
    private boolean productWarranty;
    @Column
    private int warrantyInMonths;
    @Column
    private LocalDate dateOfPurchase;

    public InvoiceModel(Long id, String invoiceId, int productNumber, String productName, double totalPrice, double vat21ProductPrice, double vat9ProductPrice, int userId, String userAddress, boolean productWarranty, int warrantyInMonths, LocalDate dateOfPurchase) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productNumber = productNumber;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.vat21ProductPrice = vat21ProductPrice;
        this.vat9ProductPrice = vat9ProductPrice;
        this.userId = userId;
        this.userAddress = userAddress;
        this.productWarranty = productWarranty;
        this.warrantyInMonths = warrantyInMonths;
        this.dateOfPurchase = dateOfPurchase;
    }

    public InvoiceModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getVat21ProductPrice() {
        return vat21ProductPrice;
    }

    public void setVat21ProductPrice(double vat21ProductPrice) {
        this.vat21ProductPrice = vat21ProductPrice;
    }

    public double getVat9ProductPrice() {
        return vat9ProductPrice;
    }

    public void setVat9ProductPrice(double vat9ProductPrice) {
        this.vat9ProductPrice = vat9ProductPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
