package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import java.util.Date;

public class WarrantyInputDto {

        public boolean productWarranty;

        public int warrantyInMoths;

        public Date warrantyStart;

        public Date warrantyEnds;

    public boolean isProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(boolean productWarranty) {
        this.productWarranty = productWarranty;
    }

    public int getWarrantyInMoths() {
        return warrantyInMoths;
    }

    public void setWarrantyInMoths(int warrantyInMoths) {
        this.warrantyInMoths = warrantyInMoths;
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

