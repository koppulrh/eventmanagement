// AttendeeController.java (Controller Layer)
package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Attendee;
import com.example.eventmanagement.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<List<Attendee>> getAllEvents() {
        List<Attendee> attendees = attendeeService.getAllEvents();
        return ResponseEntity.ok(attendees);
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

    // ✅ Filtering, Sorting, and Pagination for Attendees
    @GetMapping("/filter")
    public ResponseEntity<PagedModel<EntityModel<Attendee>>> filterAttendees(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
            PagedResourcesAssembler<Attendee> assembler) {

        Page<Attendee> attendees = attendeeService.filterAttendees(name, pageable);
        return ResponseEntity.ok(assembler.toModel(attendees));
    }
}
