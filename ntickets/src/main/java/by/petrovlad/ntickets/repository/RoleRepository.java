package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.entity.Role;
import by.petrovlad.ntickets.model.util.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
