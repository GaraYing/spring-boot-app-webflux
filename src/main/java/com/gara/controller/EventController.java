package com.gara.controller;

import com.gara.event.MyEvent;
import com.gara.repository.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: GaraYing
 * @create: 2018-10-31 15:17
 **/

@RestController
@RequestMapping("/events")
public class EventController {
    /*
        注：方便起见，直接应用Dao层
     */
//    @Autowired
//    private MyEventRepository eventRepository;

    private final MyEventRepository eventRepository;

    @Autowired
    public EventController(MyEventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        return this.eventRepository.insert(events).then();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {
        return this.eventRepository.findBy();
    }
}
