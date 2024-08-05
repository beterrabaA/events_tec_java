package com.events.api.controller;

import com.events.api.domain.event.Event;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.domain.event.EventResponseDTO;
import com.events.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description",required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("uf") String uf,
                                        @RequestParam("remote") boolean remote,
                                        @RequestParam(value = "image",required = false) MultipartFile image,
                                        @RequestParam("eventUrl") String eventUrl

    ){
        EventRequestDTO eventDTO;
        eventDTO = new EventRequestDTO(title,description,date,city,uf,remote,eventUrl,image);
        Event createdEvent = this.eventService.createEvent(eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "0") int size) {
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page,size);

        return  ResponseEntity.ok(allEvents);
    }
}
