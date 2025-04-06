// TicketController.java
package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.Ticket;
import com.example.eventmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/filter")
    public ResponseEntity<PagedModel<EntityModel<Ticket>>> filterTickets(
            @RequestParam(required = false) String ticketType,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Long eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            PagedResourcesAssembler<Ticket> assembler) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Ticket> tickets = ticketService.filterTickets(ticketType, minPrice, eventId, pageable);
        PagedModel<EntityModel<Ticket>> pagedModel = assembler.toModel(tickets);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));  // Use orElseThrow to unwrap Optional
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "Ticket deleted successfully!";
    }
}
