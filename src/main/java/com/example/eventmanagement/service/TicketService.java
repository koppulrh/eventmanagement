// TicketService.java
package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Ticket;
import com.example.eventmanagement.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(Long id) {  // Returns Optional
        return ticketRepository.findById(id);
    }


    public Page<Ticket> filterTickets(String ticketType, Double minPrice, Long eventId, Pageable pageable) {
        return ticketRepository.filterTickets(ticketType, minPrice, eventId, pageable);
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
