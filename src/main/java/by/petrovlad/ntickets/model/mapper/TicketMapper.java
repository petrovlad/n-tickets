package by.petrovlad.ntickets.model.mapper;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;

public class TicketMapper {

    private TicketMapper() {}

    public static Ticket mapToTicket(TicketDTO dto) {
        Ticket ticket = new Ticket();

        ticket.setAuthorId(dto.getAuthorId());
        ticket.setTitle(dto.getTitle());
        ticket.setContent(dto.getContent());
        ticket.setShowWarning(dto.getShowWarning());
        ticket.setReadingsCount(dto.getReadingsCount());
        ticket.setUniqueHash(dto.getUniqueHash());

        return ticket;
    }

    public static TicketDTO mapToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();

        dto.setAuthorId(ticket.getAuthorId());
        dto.setTitle(ticket.getTitle());
        dto.setContent(ticket.getContent());
        dto.setShowWarning(ticket.getShowWarning());
        dto.setReadingsCount(ticket.getReadingsCount());
        dto.setUniqueHash(ticket.getUniqueHash());

        return dto;
    }
}
