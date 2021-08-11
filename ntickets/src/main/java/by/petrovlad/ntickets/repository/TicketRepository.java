package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Iterable<Ticket> findAllByAuthorId(Long authorId);
    Optional<Ticket> findByUniqueHash(String hash);
    void deleteByUniqueHash(String hash);
    boolean existsByUniqueHash(String hash);
}
