package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.repository.UserRepository;
import by.petrovlad.ntickets.service.TicketsService;
import by.petrovlad.ntickets.service.util.IterableUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return IterableUtil.asList(ticketRepository.findAll(),
                (Ticket t) -> t.getReadingsCount() > 0,
                TicketMapper::mapToDTO);
    }

    @Override
    // handle 0
    public List<TicketDTO> getTickets(Long authorId) {
        return IterableUtil.asList(ticketRepository.findAllByAuthorId(authorId),
                (Ticket t) -> t.getReadingsCount() > 0,
                TicketMapper::mapToDTO);
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

    @Override
    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        }
    }

    @Override
    public void deleteTicket(String hash) {
        if (ticketRepository.existsByUniqueHash(hash)) {
            ticketRepository.deleteByUniqueHash(hash);
        }
    }

    @Override
    public TicketDTO updateTicket(TicketDTO dto, String hash) {
        dto.setUniqueHash(hash);
        Ticket ticket = TicketMapper.mapToTicket(dto);
        Optional<Ticket> optTicket = ticketRepository.findByUniqueHash(hash);
        // if empty - create new, else - update
        if (optTicket.isEmpty()) {
            // we need to overwrite promoted hash value because of security reasons
            // otherwise anyone can make his own hashes with any length
            return createTicket(dto);
        } else {
            Ticket ticketDB = optTicket.get();
            ticketDB.setAuthorId(ticket.getAuthorId() == null ? ticketDB.getAuthorId() : ticket.getAuthorId());
            ticketDB.setTitle(ticket.getTitle() == null ? ticketDB.getTitle() : ticket.getTitle());
            ticketDB.setContent(ticket.getContent() == null ? ticketDB.getContent() : ticket.getContent());
            ticketDB.setShowWarning(ticket.getShowWarning() == null ? ticketDB.getShowWarning() : ticket.getShowWarning());
            ticketDB.setReadingsCount(ticket.getReadingsCount() == null ? ticketDB.getReadingsCount() : ticket.getReadingsCount());

            ticketRepository.save(ticketDB);
            return TicketMapper.mapToDTO(ticketDB);
        }
    }

    private String generateUniqueHash(TicketDTO dto) {
        return Long.toHexString((long) Objects.hashCode(dto) << 32
                | (Instant.now().toEpochMilli()));
    }
}
