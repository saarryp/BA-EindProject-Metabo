package nl.bitsentools.eindprojectbackendmetabo.dto.warranty;

import java.time.LocalDate;

public class WarrantyOutputDto {

    public Long id;


    public LocalDate warrantyStart;

    public LocalDate warrantyEnds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
