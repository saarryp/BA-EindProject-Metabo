package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

public class ProductInputDto {

//   public Long id;

   public String brandName;

    public String productName;

   public int productNumber;

   public double price;

   public boolean productWarranty;
   public int warrantyInMonths;

    @Enumerated(EnumType.STRING)
   public TypeOfMachine typeOfMachine;

}
