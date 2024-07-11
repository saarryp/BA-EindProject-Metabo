package nl.bitsentools.eindprojectbackendmetabo.dto.user;

import nl.bitsentools.eindprojectbackendmetabo.models.Authority;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserInputDto {

    @NotBlank(message = "Username is verplicht.")
    @Size(min = 6, message = "Username moet minimaal 6 tekens bevatten.")
    public String username;

    @NotBlank(message = "Wachtwoord is verplicht. ")
    @Size(message = "Wachtwoord moet minimaal 6 tekens bevatten.")

    public String password;
    public Boolean enabled;
    public String apikey;
    @NotBlank(message = "E-mailadres is verplicht")
    @Size(min = 6, message = "minimaal 6 tekens en vergeet de @ niet." )
    public String email;

    @NotBlank(message = "Verplicht om postadres of huisadres met straatnaam, huisnummer, postcode en stad in te voeren.")
    public String userDetails;
    public Set<Authority>authorities;

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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
