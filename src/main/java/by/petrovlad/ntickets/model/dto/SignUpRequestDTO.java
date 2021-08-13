package by.petrovlad.ntickets.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

public class SignUpRequestDTO {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    private String username;

    private List<String> roles;

    public SignUpRequestDTO() {}

    public SignUpRequestDTO(String email, String password, String username, List<String> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpRequestDTO request = (SignUpRequestDTO) o;
        return username.equals(request.username)
                && email.equals(request.email)
                && roles.equals(request.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, roles);
    }
}

