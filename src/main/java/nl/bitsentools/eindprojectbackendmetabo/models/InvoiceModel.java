package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")

public class InvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column

    private Long id;
    @Column
    private String invoiceId;

    @Column
    private double totalPrice;
    @Column
    private double vat21ProductPrice;
    @Column
    private double vat9ProductPrice;
    @Column
    private double netPriceWithoutVat;
    @Column
    private double vatRate;
    @Column
    private int userId;
    @Column
    private String userAddress;
    @Column
    private boolean productWarranty;

    @Column
    private LocalDate dateOfPurchase;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "warranty_id")
    WarrantyModel warrantyModel;

    @OneToOne
    @JoinColumn(name = "order_id")
    OrderModel orderModel;


    public InvoiceModel(Long id, String invoiceId, double totalPrice, double vat21ProductPrice, double vat9ProductPrice, double netPriceWithoutVat,double vatRate,  int userId, String userAddress, boolean productWarranty, LocalDate dateOfPurchase, WarrantyModel warrantyModel, OrderModel orderModel) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.totalPrice = totalPrice;
        this.vat21ProductPrice = vat21ProductPrice;
        this.vat9ProductPrice = vat9ProductPrice;
        this.netPriceWithoutVat = netPriceWithoutVat;
        this.vatRate = vatRate;
        this.userId = userId;
        this.userAddress = userAddress;
        this.productWarranty = productWarranty;
        this.dateOfPurchase = dateOfPurchase;
        this.warrantyModel = warrantyModel;
        this.orderModel = orderModel;
    }

    public InvoiceModel(){}

    public Long getId() {
        return id;
    }

    public double getNetPriceWithoutVat() {
        return netPriceWithoutVat;
    }

    public void setNetPriceWithoutVat(double netPriceWithoutVat) {
        this.netPriceWithoutVat = netPriceWithoutVat;
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

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
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

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public WarrantyModel getWarrantyModel() {
        return warrantyModel;
    }

    public void setWarrantyModel(WarrantyModel warrantyModel) {
        this.warrantyModel = warrantyModel;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
}

