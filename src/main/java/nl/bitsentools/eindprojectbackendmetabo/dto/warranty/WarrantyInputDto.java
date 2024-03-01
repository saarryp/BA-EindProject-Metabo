package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import java.util.Date;

public class WarrantyInputDto {

       public String productName;
        public Date warrantyStart;

        public Date warrantyEnds;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Date getWarrantyStart() {
        return warrantyStart;
    }

    public void setWarrantyStart(Date warrantyStart) {
        this.warrantyStart = warrantyStart;
    }

    public Date getWarrantyEnds() {
        return warrantyEnds;
    }

    public void setWarrantyEnds(Date warrantyEnds) {
        this.warrantyEnds = warrantyEnds;
    }
}

