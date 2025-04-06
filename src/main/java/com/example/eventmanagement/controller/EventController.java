// EventController.java
package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event); // This line requires saveEvent() method in EventService.java
    }

    @GetMapping("/filter")
    public Page<Event> filterEvents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startTime") String sortBy) {

        return eventService.filterEvents(name, location, startTime, endTime, page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully!";
    }
}
