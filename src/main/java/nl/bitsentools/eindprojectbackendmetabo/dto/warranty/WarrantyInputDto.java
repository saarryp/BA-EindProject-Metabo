package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import java.time.LocalDate;

public class WarrantyInputDto {

       public String productName;
        public LocalDate warrantyStart;

        public LocalDate warrantyEnds;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public LocalDate getWarrantyStart() {
        return warrantyStart;
    }

    public void setWarrantyStart(LocalDate warrantyStart) {
        this.warrantyStart = warrantyStart;
    }

    public LocalDate getWarrantyEnds() {
        return warrantyEnds;
    }

    public void setWarrantyEnds(LocalDate warrantyEnds) {
        this.warrantyEnds = warrantyEnds;
    }
}

