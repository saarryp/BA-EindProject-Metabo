package nl.bitsentools.eindprojectbackendmetabo.dto.stock;

public class StockOutputDto {

public Long id;

public int weeksToDelivery;

public int productSold;
public int quantityInStock;
public boolean outOfStock;

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
