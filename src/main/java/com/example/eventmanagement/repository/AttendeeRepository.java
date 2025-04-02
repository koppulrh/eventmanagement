// AttendeeRepository.java (Repository Layer)
package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
