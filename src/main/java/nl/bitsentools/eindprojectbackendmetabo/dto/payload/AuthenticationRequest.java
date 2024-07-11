package nl.bitsentools.eindprojectbackendmetabo.dto.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthenticationRequest {

    @NotBlank(message = "Gebruikersnaam mag niet leeg zijn.")
    private String username;

    @NotBlank(message = "Wachtwoord mag niet leeg zijn.")
    @Size(min = 6, message = "Wachtwoord moet minimaal 6 tekens lang zijn.")
    String password;

    public AuthenticationRequest() {
    }
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

}
