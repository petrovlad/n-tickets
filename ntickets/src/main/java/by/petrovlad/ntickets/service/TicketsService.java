package by.petrovlad.ntickets.service;

import by.petrovlad.ntickets.model.dto.TicketDTO;

import java.util.List;

public interface TicketsService {
    List<TicketDTO> getTickets();
    List<TicketDTO> getTickets(Long authorId);
    TicketDTO createTicket(TicketDTO dto);
    void deleteTicket(Long id);
    void deleteTicket(String hash);
}
