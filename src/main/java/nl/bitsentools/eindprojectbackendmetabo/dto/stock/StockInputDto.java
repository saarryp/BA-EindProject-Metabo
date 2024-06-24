package nl.bitsentools.eindprojectbackendmetabo.dto.stock;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class StockInputDto {

        public LocalDate orderPlacedDate;

        @Min(value = 0, message = "Aantal weken voor levering moet 0 of groter zijn.")
        public Integer weeksToDelivery;

        @Min(value = 0, message = "Aantalverkochte producten moet minimaal 0 of meer zijn.")
        public Integer productSold;

        @Min(value= 0, message = "Aantal producten op voorraad moet minimaal 0 of meer zijn.")
        public Integer quantityInStock;

        public boolean outOfStock;


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
