package by.petrovlad.ntickets.models;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "u_login"),
        @UniqueConstraint(columnNames = "u_email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "u_login", nullable = false)
    private String login;

    @Column(name = "u_email", nullable = false)
    private String email;

    @Column(name = "u_password", nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User() {}
    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
