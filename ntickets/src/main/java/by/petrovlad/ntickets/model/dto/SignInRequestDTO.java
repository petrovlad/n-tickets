package by.petrovlad.ntickets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignInRequestDTO {
/*    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

/*    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
