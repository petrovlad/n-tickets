package by.petrovlad.ntickets.repository;

import by.petrovlad.ntickets.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
