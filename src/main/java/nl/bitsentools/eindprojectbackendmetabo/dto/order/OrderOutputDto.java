package nl.bitsentools.eindprojectbackendmetabo.dto.order;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;

import java.util.List;

public class OrderOutputDto {

    public Long id;

    public int userId;

    public String userEmail;

    public String userDetails;

    public int orderNumber;

//
    public List<ProductOutputDto>productDto;
    public double price;
    public int quantity;

    public double totalPriceOrder;



    public OrderOutputDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ProductOutputDto> getProductDto() {
        return productDto;
    }

    public void setProductDto(List<ProductOutputDto> productDto) {
        this.productDto = productDto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPriceOrder() {
        return totalPriceOrder;
    }

    public void setTotalPriceOrder(double totalPriceOrder) {
        this.totalPriceOrder = totalPriceOrder;
    }
}
