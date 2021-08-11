package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.repository.UserRepository;
import by.petrovlad.ntickets.service.TicketsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
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
        return StreamSupport
                .stream(ticketRepository.findAll().spliterator(), false)
                .map(TicketMapper::mapToDTO)
                .filter(ticketDTO -> ticketDTO.getReadingsCount() > 0)
                .collect(Collectors.toList());
    }

    @Override
    // handle 0
    public List<TicketDTO> getTickets(Long authorId) {
        return StreamSupport
                .stream(ticketRepository.findAllByAuthorId(authorId).spliterator(), false)
                .map(TicketMapper::mapToDTO)
                .filter(ticketDTO -> ticketDTO.getReadingsCount() > 0)
                .collect(Collectors.toList());
    }

    @Override
    // check if user exists
    public TicketDTO createTicket(TicketDTO dto) {
        // generate empty(or not?) hash value
        dto.setUniqueHash(generateUniqueHash(dto));

        Ticket ticket = TicketMapper.mapToTicket(dto);
        ticket = ticketRepository.save(ticket);
        return TicketMapper.mapToDTO(ticket);
    }

    public String generateUniqueHash(TicketDTO dto) {
        return Long.toHexString((long) Objects.hashCode(dto) << 32
                | (Instant.now().toEpochMilli()));
    }
}
