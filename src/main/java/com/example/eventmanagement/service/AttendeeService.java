// AttendeeService.java
package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Attendee;
import com.example.eventmanagement.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    // Create Attendee
    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    // Get Attendee By ID
    public Optional<Attendee> getAttendeeById(Long id) {
        return attendeeRepository.findById(id);
    }

    // Get All Attendees
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    // ✅ Filter Attendees with Pagination & Sorting
    public Page<Attendee> filterAttendees(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return attendeeRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            return attendeeRepository.findAll(pageable);
        }
    }

    // Update Attendee
    public Attendee updateAttendee(Long id, Attendee attendee) {
        attendee.setId(id);
        return attendeeRepository.save(attendee);
    }

    // Delete Attendee
    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }

    public List<Attendee> getAllEvents() {
        return attendeeRepository.findAll();
    }
}
