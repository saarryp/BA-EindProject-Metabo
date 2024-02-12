package nl.bitsentools.eindprojectbackendmetabo.dto.stock;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class StockInputDto {


        public String brandName;

        public String productName;

        public int productNumber;
        @Enumerated(EnumType.STRING)
        public String typeOfMachine;

        public int productInStock;

        public Date orderPlacedDate;


        public int weeksToDelivery;


        public int productSold;

        public int quantityInStock;

        public boolean outOfStock;





}
