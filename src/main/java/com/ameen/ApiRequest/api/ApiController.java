package com.ameen.ApiRequest.api;

import com.ameen.ApiRequest.model.Rate;
import com.ameen.ApiRequest.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    private static Logger log = LoggerFactory.getLogger(ApiController.class);

    private final RateService rateService;

    @GetMapping("/get/{code}")
    public Mono<String> getRate(@PathVariable String code){
        return Mono.just(code).doOnNext(x-> log.info("started request in {}", x))
                .flatMap(x ->rateService.getRateById(x))
                .map(Object::toString)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find rate")))
                .doOnError(error -> log.info("Error processing request {}",error.toString()))
                .onErrorResume(e-> Mono.just("Unable to find currency"));
    }

    @GetMapping("/getall")
    public Flux<String> getAllRate(){
        log.info("got request:");
        return rateService.getAllRate()
                .doOnNext(x-> log.info("returning request : {}", x))
                .map(Object::toString)
                .switchIfEmpty(Mono.error(new RuntimeException("No rate found")))
                .doOnError(error -> log.info("Error processing request {}",error.toString()))
                .onErrorResume(e-> Mono.just("Unable to find currency"));
    }

    @PostMapping("/update")
    public Mono<Void> updateRate(@RequestBody Rate rate){
        rate.setIsNew(Boolean.FALSE);
        return rateService.addRate(rate).then();
    }

    @PostMapping("/add")
    public Mono<Void> addRate(@RequestBody Rate rate){
        rate.setIsNew(Boolean.TRUE);
        return rateService.addRate(rate).then();
    }

}
