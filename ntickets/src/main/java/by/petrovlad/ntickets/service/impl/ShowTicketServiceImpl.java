package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.exception.ResourceNotFoundException;
import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.service.ShowTicketService;
import org.springframework.stereotype.Service;

@Service
public class ShowTicketServiceImpl implements ShowTicketService {
    private static final String EXC_TICKET_NOT_FOUND = "Cannot find ticket with hash = ";
    private final TicketRepository ticketRepository;

    public ShowTicketServiceImpl(TicketRepository repository) {
        this.ticketRepository = repository;
    }

    @Override
    public TicketDTO getTicket(String ticketHash) throws ResourceNotFoundException {
        Ticket ticket = ticketRepository
                .findByUniqueHash(ticketHash)
                .orElseThrow(() ->  new ResourceNotFoundException(EXC_TICKET_NOT_FOUND + ticketHash));

        // if read's count is 0 then ticket is no longer available,
        // but we don't delete ticket from db (because we are sneaky boys), so we just don't send it to client
        if (ticket.getReadingsCount() == 0) { throw new ResourceNotFoundException(EXC_TICKET_NOT_FOUND + ticketHash); }

        // first we decrement readings counter, and then send it to the client
        ticket.setReadingsCount(ticket.getReadingsCount() - 1);
        ticketRepository.save(ticket);

        return TicketMapper.mapToDTO(ticket);
    }
}
