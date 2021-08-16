package by.petrovlad.ntickets.service.impl;

import by.petrovlad.ntickets.exception.ResourceNotFoundException;
import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.entity.User;
import by.petrovlad.ntickets.model.mapper.TicketMapper;
import by.petrovlad.ntickets.repository.TicketRepository;
import by.petrovlad.ntickets.repository.UserRepository;
import by.petrovlad.ntickets.security.service.UserDetailsImpl;
import by.petrovlad.ntickets.service.TicketsService;
import by.petrovlad.ntickets.service.util.IterableUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.petrovlad.ntickets.model.util.RoleType.ROLE_ADMIN;

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
        UserDetailsImpl userDetails = getCurrentUserDetails();
        if (userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(ROLE_ADMIN.name()))) {
            // if the user has role 'ADMIN', then return all tickets
            return IterableUtil.asList(ticketRepository.findAll(),
                    (Ticket t) -> t.getReadingsCount() > 0,
                    TicketMapper::mapToDTO);
        } else {
            // otherwise, return only specific user tickets
            return getTickets(userDetails.getId());
        }
    }

    @Override
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

        UserDetailsImpl userDetails = getCurrentUserDetails();

        Ticket ticket = TicketMapper.mapToTicket(dto);
        ticket.setAuthorId(userDetails.getId());
        ticket = ticketRepository.save(ticket);
        return TicketMapper.mapToDTO(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        UserDetailsImpl userDetails = getCurrentUserDetails();
        if (ticketRepository.existsByIdAndAuthorId(id, userDetails.getId())) {
            ticketRepository.deleteById(id);
        }
    }

    @Override
    public void deleteTicket(String hash) {
        UserDetailsImpl userDetails = getCurrentUserDetails();
        if (ticketRepository.existsByUniqueHashAndAuthorId(hash, userDetails.getId())) {
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

            UserDetailsImpl userDetails = getCurrentUserDetails();
            // only owner can change ticket
            if (ticketDB.getAuthorId().equals(userDetails.getId())) {
                ticketDB.setAuthorId(ticket.getAuthorId() == null ? ticketDB.getAuthorId() : ticket.getAuthorId());
                ticketDB.setTitle(ticket.getTitle() == null ? ticketDB.getTitle() : ticket.getTitle());
                ticketDB.setContent(ticket.getContent() == null ? ticketDB.getContent() : ticket.getContent());
                ticketDB.setShowWarning(ticket.getShowWarning() == null ? ticketDB.getShowWarning() : ticket.getShowWarning());
                ticketDB.setReadingsCount(ticket.getReadingsCount() == null ? ticketDB.getReadingsCount() : ticket.getReadingsCount());

                ticketRepository.save(ticketDB);
            }
            return TicketMapper.mapToDTO(ticketDB);
        }
    }

    private UserDetailsImpl getCurrentUserDetails() {
        return (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
    }

    private String generateUniqueHash(TicketDTO dto) {
        return Long.toHexString((long) Objects.hashCode(dto) << 32
                | (Instant.now().toEpochMilli()));
    }
}
