// AttendeeService.java (Service Layer)
package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Attendee;
import com.example.eventmanagement.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    public Optional<Attendee> getAttendeeById(Long id) {
        return attendeeRepository.findById(id);
    }

    public Attendee updateAttendee(Long id, Attendee attendee) {
        attendee.setId(id);
        return attendeeRepository.save(attendee);
    }

    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }
}
