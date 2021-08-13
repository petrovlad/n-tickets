package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.service.ShowTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-{ticketHash:[A-Za-z0-9]+}")
public class ShowTicketController {

    private final ShowTicketService showTicketService;

    @Autowired
    public ShowTicketController(ShowTicketService showTicketService) {
        this.showTicketService = showTicketService;
    }

    @GetMapping
    public TicketDTO getTicket(@PathVariable String ticketHash) {
        return showTicketService.getTicket(ticketHash);
    }
}
