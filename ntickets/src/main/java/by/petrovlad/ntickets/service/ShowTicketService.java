package by.petrovlad.ntickets.service;

import by.petrovlad.ntickets.exception.ResourceNotFoundException;
import by.petrovlad.ntickets.model.dto.TicketDTO;

public interface ShowTicketService {
    TicketDTO getTicket(String ticketHash) throws ResourceNotFoundException;
}
