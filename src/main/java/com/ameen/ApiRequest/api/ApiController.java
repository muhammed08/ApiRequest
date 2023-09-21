package com.ameen.ApiRequest.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class ApiController {

    @GetMapping("/{id1}/{id2}")
    public Flux<Integer> getNumber(@PathVariable String id1,@PathVariable String id2){
        return Flux.range(Integer.parseInt(id1),Integer.parseInt(id2))
                .log();
    }

}
