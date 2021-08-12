package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
}
