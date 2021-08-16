package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Iterable<Ticket> findAllByAuthorId(Long authorId);
    Optional<Ticket> findByUniqueHash(String hash);
    @Transactional
    void deleteByUniqueHash(String hash);
    boolean existsByUniqueHash(String hash);
    boolean existsByUniqueHashAndAuthorId(String hash, Long authorId);
    boolean existsByIdAndAuthorId(Long id, Long authorId);
}
