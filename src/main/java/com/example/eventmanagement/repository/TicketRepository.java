// TicketRepository.java (Repository Layer)
package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    @Query("SELECT t FROM Ticket t WHERE " +
            "(:ticketType IS NULL OR t.ticketType = :ticketType) AND " +
            "(:minPrice IS NULL OR t.price >= :minPrice) AND " +
            "(:eventId IS NULL OR t.event.id = :eventId)")
    Page<Ticket> filterTickets(@Param("ticketType") String ticketType,
                               @Param("minPrice") Double minPrice,
                               @Param("eventId") Long eventId,
                               Pageable pageable);
}
