package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(AuthorityKey.class)
@Table(name= "authorities")


public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private Long id;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority(){}

    public Authority(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
