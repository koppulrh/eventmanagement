// EventRepository.java
package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE " +
            "(COALESCE(:name, '') = '' OR e.name LIKE %:name%) AND " +
            "(COALESCE(:location, '') = '' OR e.location LIKE %:location%) AND " +
            "(COALESCE(:startTime, null) IS NULL OR e.startTime >= :startTime) AND " +
            "(COALESCE(:endTime, null) IS NULL OR e.endTime <= :endTime)")
    Page<Event> filterEvents(String name, String location, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
}
