package by.petrovlad.ntickets.model.mapper;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;

public class TicketMapper {
    public static Ticket mapToTicket(TicketDTO dto) {
        Ticket ticket = new Ticket();

        ticket.setId(dto.getId());
        ticket.setAuthorId(dto.getAuthorId());
        ticket.setTitle(dto.getTitle());
        ticket.setContent(dto.getContent());
        ticket.setShowWarning(dto.getShowWarning());
        ticket.setReadingsCount(dto.getReadingsCount());
        ticket.setHash(Integer.valueOf(dto.getHash(), 16));

        return ticket;
    }

    public static TicketDTO mapToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();

        dto.setId(ticket.getId());
        dto.setAuthorId(ticket.getAuthorId());
        dto.setTitle(ticket.getTitle());
        dto.setContent(ticket.getContent());
        dto.setShowWarning(ticket.getShowWarning());
        dto.setReadingsCount(ticket.getReadingsCount());
        dto.setHash(Integer.toHexString(ticket.getHash()));

        return dto;
    }
}
