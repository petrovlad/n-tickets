package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
