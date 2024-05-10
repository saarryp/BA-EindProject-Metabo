package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class ProductOutputDto {

    public Long id;

    @NotBlank(message = "Merknaam is niet ingevoerd.")
    public String brandName;

    @NotBlank(message = "Productnaam is niet ingevoerd")
    public String productName;

    @NotBlank(message = "Het productnummer moet ingevoerd zijn.")
    @NotNull(message = "Het productnummer moet groter dan 0 zijn.")
    public int productNumber;

    @NotNull(message = "De prijs moet zijn ingevoerd.")
    public double price;

    @NotBlank(message = "Het type machine is niet ingevoerd.")
    @Enumerated(EnumType.STRING)
    public TypeOfMachine typeOfMachine;

    public List<String> imageUrls;


    public ProductOutputDto() {
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

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
