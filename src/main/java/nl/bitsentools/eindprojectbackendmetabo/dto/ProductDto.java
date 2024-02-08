package nl.bitsentools.eindprojectbackendmetabo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

public class ProductDto {

   public Long id;

   public String brandName;

    public String productName;

   public int productNumber;

   public double price;


    @Enumerated(EnumType.STRING)
   public TypeOfMachine typeOfMachine;

}
