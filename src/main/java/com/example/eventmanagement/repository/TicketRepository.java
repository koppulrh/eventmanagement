// TicketRepository.java (Repository Layer)
package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
