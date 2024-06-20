package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class WarrantyInputDto {


       public LocalDate warrantyStart;

       public LocalDate warrantyEnds;

       public Long productModelId;



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

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }
}

