// AttendeeRepository.java
package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Attendee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    @Query("SELECT a FROM Attendee a WHERE " +
            "(COALESCE(:name, '') = '' OR a.name LIKE %:name%) AND " +
            "(COALESCE(:email, '') = '' OR a.email LIKE %:email%) AND " +
            "(COALESCE(:eventId, null) IS NULL OR a.event.id = :eventId)")
    Page<Attendee> filterAttendees(String name, String email, Long eventId, Pageable pageable);
    Page<Attendee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
