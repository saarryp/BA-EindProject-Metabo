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
    private int weeksToDelivery;

    @Column
    private int productSold;
    @Column
    private int quantityInStock;
    @Column
    private boolean outOfStock;



    public StockModel(Long id, int weeksToDelivery, int productSold, int quantityInStock, boolean outOfStock) {
        this.id = id;
        this.weeksToDelivery = weeksToDelivery;
        this.productSold = productSold;
        this.quantityInStock = quantityInStock;
        this.outOfStock = outOfStock;
    }

    public StockModel() {

    }

public Long getId() {
    return id;
}


    public void setId(Long id) {
        this.id = id;
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


}
