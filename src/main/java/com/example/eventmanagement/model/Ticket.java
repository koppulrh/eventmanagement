// Ticket.java (Model Layer)
package com.example.eventmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticketType; // e.g., VIP, Standard, Early Bird

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
