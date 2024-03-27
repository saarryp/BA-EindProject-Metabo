package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class ProductInputDto {

//   public Long id;

    @NotBlank(message = "Merknaam moet worden toegevoegd.")
   public String brandName;


    @NotBlank(message = "Productnaam moet worden toegevoegd.")
    public String productName;

    @Min(value = 1, message = "Productnummer heeft minimaal 1 cijfer.")
    public int productNumber;

    @DecimalMin(value = "0,01", message = "Prijs moet minimaal 0,01 zijn.")
    @DecimalMax(value = "1.000.000", message = "Prijs mag maximaal 1.000.000 euros zijn.")
    public double price;

    public boolean productWarranty;
    public int warrantyInMonths;

    @NotBlank(message = "Type machine moet worden ingevoerd.")
    @Enumerated(EnumType.STRING)
   public TypeOfMachine typeOfMachine;

    public MultipartFile imageFile;

}
