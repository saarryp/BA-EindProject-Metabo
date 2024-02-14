package nl.bitsentools.eindprojectbackendmetabo.dto.order;

public class OrderInputDto {
    //deze input beperkt omdat USER GEEN AANPASSINGEN MAG DOEN IN BEPAALDE ONDERDELEN VAN DE GEGEVENS.
    //nog even aan het bomen of de ADmin hier wel een andere rol in krijgt.
    public String userEmail;
    public String userDetails;

    public int numberOfProducts;

    public int productNumber;


}
