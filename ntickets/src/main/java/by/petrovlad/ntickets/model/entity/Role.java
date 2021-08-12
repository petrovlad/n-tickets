package by.petrovlad.ntickets.model.entity;

import by.petrovlad.ntickets.model.util.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "r_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "r_name", length = 20, nullable = false)
    private RoleType name;

    public Role() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}
