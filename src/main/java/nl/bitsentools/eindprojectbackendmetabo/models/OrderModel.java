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

}
