package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel {


    @GeneratedValue
    @Id
    @Column
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

//    @OneToMany(
//            targetEntity = Authority.class,
//            mappedBy = "username",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.EAGER)
//    private Set<Authority> authorities = new HashSet<>();


    @Column(nullable = false)
    private boolean enabled = true;
    //zachte uitschakeling van bijvoorbeeld een user. Mocht deze zich uitschriven van een account, dan blijven de invoices wel bestaan als deze op enabled = false wordt gezet

    @Column
    private  String apiKey;

    @Column
    private String email;

    @Column
    private String userDetails;

    public UserModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

}
