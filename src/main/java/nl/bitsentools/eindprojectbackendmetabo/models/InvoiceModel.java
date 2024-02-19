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

}
