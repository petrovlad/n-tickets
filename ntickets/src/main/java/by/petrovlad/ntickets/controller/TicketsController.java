package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping
    public Ticket newTicket(@RequestBody Ticket ticket) {
        ticket = ticketRepository.save(ticket);
        return ticket;
    }
}
