package nl.bitsentools.eindprojectbackendmetabo.models;


import jakarta.persistence.*;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.UserRole;

@Entity
@Table(name = "roles")
public class UserModel {
    @Id
    @Column

    private UserRole role;
    private String userName;

    @Column
    private String password;

}
