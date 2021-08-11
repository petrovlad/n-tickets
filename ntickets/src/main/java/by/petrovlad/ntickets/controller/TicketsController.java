package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.service.TicketsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping
    public List<TicketDTO> getTickets() {
        return ticketsService.getTickets();
    }

    @PostMapping
    public TicketDTO newTicket(@Valid @RequestBody TicketDTO ticket) {
        return ticketsService.createTicket(ticket);
    }

    @DeleteMapping("{hash}")
    public void deleteTicket(@PathVariable String hash) {
        ticketsService.deleteTicket(hash);
    }
}
