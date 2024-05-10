package nl.bitsentools.eindprojectbackendmetabo.dto.user;

import nl.bitsentools.eindprojectbackendmetabo.models.Authority;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserOutputDto {

    public Long id;

    @NotBlank (message = "Gebruiksnaam mag niet leeg zijn")
    public String username;

    @NotBlank(message = "Wachtwoord mag niet leeg zijn.")
    public String password;

    public Boolean enabled;
    public String apikey;
    @NotBlank(message = "E-mailadres mag niet leeg zijn.")
    public String email;

    @NotBlank(message = "dit veld met gegevens mag niet leeg zijn.")
    public String userDetails;

    public Set<Authority> authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }
}
