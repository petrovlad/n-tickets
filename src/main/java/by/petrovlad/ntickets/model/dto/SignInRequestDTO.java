package by.petrovlad.ntickets.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SignInRequestDTO {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public SignInRequestDTO() {}

    public SignInRequestDTO(String username, String password) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInRequestDTO request = (SignInRequestDTO) o;
        return username.equals(request.username) && password.equals(request.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
