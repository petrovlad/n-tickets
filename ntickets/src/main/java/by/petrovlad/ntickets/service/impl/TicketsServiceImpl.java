package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.entity.User;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.repository.UserRepository;
import by.petrovlad.ntickets.service.TicketsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketsServiceImpl implements TicketsService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketsServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TicketDTO> getTickets() {
        List<TicketDTO> tickets = StreamSupport
                .stream(ticketRepository.findAll().spliterator(), false)
                .map(TicketMapper::mapToDTO)
                .collect(Collectors.toList());
        return tickets;
    }

    @Override
    // handle 0
    public List<TicketDTO> getTickets(Long authorId) {
        List<TicketDTO> tickets = StreamSupport
                .stream(ticketRepository.findAllByAuthorId(authorId).spliterator(), false)
                .map(TicketMapper::mapToDTO)
                .collect(Collectors.toList());
        return tickets;
    }

    @Override
    // check if user exists
    public TicketDTO createTicket(TicketDTO dto) {
        Ticket ticket = TicketMapper.mapToTicket(dto);
        ticket = ticketRepository.save(ticket);
        return TicketMapper.mapToDTO(ticket);
    }
}
