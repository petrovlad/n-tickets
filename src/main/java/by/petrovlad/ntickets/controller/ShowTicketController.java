package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.service.ShowTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
