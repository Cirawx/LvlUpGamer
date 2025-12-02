package com.levelup.gaming.controllers;

import com.levelup.gaming.models.Event;
import com.levelup.gaming.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/admin")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        if (event.getId() == null) {
            event.setId(java.util.UUID.randomUUID().toString());
        }
        Event saved = eventRepository.save(event);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/admin")
    public ResponseEntity<Event> updateEvent(@PathVariable String id,
            @RequestBody com.levelup.gaming.dto.EventUpdateDTO eventUpdate) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    if (eventUpdate.getTitle() != null)
                        existingEvent.setTitle(eventUpdate.getTitle());
                    if (eventUpdate.getDate() != null)
                        existingEvent.setDate(eventUpdate.getDate());
                    if (eventUpdate.getTime() != null)
                        existingEvent.setTime(eventUpdate.getTime());
                    if (eventUpdate.getLocation() != null)
                        existingEvent.setLocation(eventUpdate.getLocation());
                    if (eventUpdate.getMapEmbed() != null)
                        existingEvent.setMapEmbed(eventUpdate.getMapEmbed());
                    if (eventUpdate.getMapUrl() != null)
                        existingEvent.setMapUrl(eventUpdate.getMapUrl());
                    if (eventUpdate.getNotes() != null)
                        existingEvent.setNotes(eventUpdate.getNotes());

                    Event saved = eventRepository.save(existingEvent);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
