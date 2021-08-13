package by.petrovlad.ntickets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class JwtResponseDTO {

    private String token;
    private final String type = "Bearer";
    private Long id;
    private String email;
    private String username;

    public JwtResponseDTO() {}

    public JwtResponseDTO(String token, Long id, String email, String username) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        JwtResponseDTO response = (JwtResponseDTO) o;
        return token.equals(response.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}