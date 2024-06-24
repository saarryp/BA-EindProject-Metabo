package nl.bitsentools.eindprojectbackendmetabo.dto.id;

import javax.validation.constraints.NotNull;

public class IdInputDto {
    @NotNull
    public  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
