package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.exception.ResourceNotFoundException;
import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.service.ShowTicketService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class ShowTicketServiceImpl implements ShowTicketService {
    private final TicketRepository ticketRepository;

    public ShowTicketServiceImpl(TicketRepository repository) {
        this.ticketRepository = repository;
    }

    @Override
    public TicketDTO getTicket(Long ticketId) throws ResourceNotFoundException {
        Ticket ticket = ticketRepository
                .findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot find ticket with id = %d", ticketId)));
        return TicketMapper.mapToDTO(ticket);
    }

    @Override
    public TicketDTO getTicket(String ticketHash) throws ResourceNotFoundException {
        Integer hash = Integer.valueOf(ticketHash, 16);
        Ticket ticket = ticketRepository
                .findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot find ticket with hash = %d", ticketHash)));
        return TicketMapper.mapToDTO(ticket);
    }
}
