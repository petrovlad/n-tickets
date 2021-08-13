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

    public Role(Integer id, RoleType name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }
}
