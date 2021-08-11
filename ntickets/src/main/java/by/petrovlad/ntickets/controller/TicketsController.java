package by.petrovlad.ntickets.controller;

import by.petrovlad.ntickets.model.Ticket;
import by.petrovlad.ntickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping()
    public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping()
    public String newTicket(HttpServletRequest request) {
        String response = "";
        try {
            long authorId = Integer.parseInt(request.getParameter("author_id"));
            int readCount = Integer.parseInt(request.getParameter("read_count"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            boolean showWarn = Boolean.parseBoolean(request.getParameter("show_warning"));

            Ticket ticket = new Ticket(authorId, title, content, showWarn, readCount);

            ticket = ticketRepository.save(ticket);
            response = "Ticket %d has been created!".formatted(ticket.getId());
        } catch (IllegalArgumentException ex) {
            response = "Some error occurs" + ex.getLocalizedMessage();
        }
        return response;
    }
}
