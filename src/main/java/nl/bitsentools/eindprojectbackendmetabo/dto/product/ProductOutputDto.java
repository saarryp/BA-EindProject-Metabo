package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

//alle GET POST EN PUT methodes. Overal waar j ehet Product een returnwaarde wil geven
//input geen validatie nodig

public class ProductOutputDto {

    public Long id;
    public String brandName;

    public String productName;

    public int productNumber;

    public double price;


    @Enumerated(EnumType.STRING)
    public TypeOfMachine typeOfMachine;
}
