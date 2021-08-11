package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.service.ShowTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket-{ticketHash:[A-Za-z0-9]+}")
public class ShowTicketController {

    private final ShowTicketService showTicketService;

    @GetMapping
    public TicketDTO getTicket(@PathVariable String ticketHash) {
        return showTicketService.getTicket(ticketHash);
    }
}
