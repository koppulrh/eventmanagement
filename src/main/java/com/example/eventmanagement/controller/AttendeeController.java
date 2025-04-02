// AttendeeController.java (Controller Layer)
package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Attendee;
import com.example.eventmanagement.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendee")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping
    public Attendee createAttendee(@RequestBody Attendee attendee) {
        return attendeeService.saveAttendee(attendee);
    }

    @GetMapping
    public List<Attendee> getAllAttendees() {
        return attendeeService.getAllAttendees();
    }

    @GetMapping("/{id}")
    public Attendee getAttendeeById(@PathVariable Long id) {
        return attendeeService.getAttendeeById(id).orElseThrow(() -> new RuntimeException("Attendee not found"));
    }

    @PutMapping("/{id}")
    public Attendee updateAttendee(@PathVariable Long id, @RequestBody Attendee attendee) {
        return attendeeService.updateAttendee(id, attendee);
    }

    @DeleteMapping("/{id}")
    public String deleteAttendee(@PathVariable Long id) {
        attendeeService.deleteAttendee(id);
        return "Attendee deleted successfully!";
    }
}
