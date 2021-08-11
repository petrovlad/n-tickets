package by.petrovlad.ntickets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-{ticketHash:[A-Za-z0-9]+}")
public class ShowTicketController {
    @GetMapping()
    public String getTicket(@PathVariable String ticketHash) {
        return ticketHash;
    }
}
