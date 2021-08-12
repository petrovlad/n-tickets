package by.petrovlad.ntickets.model.entity;

import by.petrovlad.ntickets.model.util.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "r_name", length = 20, nullable = false)
    private ERole role;

    public Role() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
