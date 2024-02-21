package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import java.util.Date;

public class WarrantyOutputDto {

    public Long id;

    public boolean productWarranty;

    public int warrantyInMonths;

    public Date warrantyStart;

    public Date warrantyEnds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(boolean productWarranty) {
        this.productWarranty = productWarranty;
    }

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
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
