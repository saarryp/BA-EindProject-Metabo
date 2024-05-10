package nl.bitsentools.eindprojectbackendmetabo.dto.stock;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class StockOutputDto {

public Long id;

@NotBlank(message = "Merknaam toevoegen is verplicht.")
public String brandName;

@NotBlank(message = "Productnaam toevoegen is verplicht.")
public String productName;

@PositiveOrZero(message = "Productnummer moet een positief getal of 0  zijn")
public int productNumber;

@Enumerated(EnumType.STRING)
public TypeOfMachine typeOfMachine;

@PositiveOrZero(message = "Producten op voorraad moet minimaal 0 of groter zijn.")
public int productInStock;

public LocalDate orderPlacedDate;
public int weeksToDelivery;

@PositiveOrZero(message = "Aantal verkochte producten moet 0 of meer zijn.")
public int productSold;

@PositiveOrZero(message = "Aantal producten op voorraad moet minimaal 0 of meer zijn.")
    public int quantityInStock;


public boolean outOfStock;

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

  public TypeOfMachine getTypeOfMachine() {
    return typeOfMachine;
  }

  public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
    this.typeOfMachine = typeOfMachine;
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
}
