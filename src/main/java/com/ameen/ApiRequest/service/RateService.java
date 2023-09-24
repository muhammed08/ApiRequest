package com.ameen.ApiRequest.service;

import com.ameen.ApiRequest.model.Rate;
import com.ameen.ApiRequest.repository.RateRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateService {

    private final RateRepository rateRepository;
    public Mono<Float> getRateById(String code){
        return rateRepository.findById(code)
                .doOnNext(x-> log.info("got response from db {}", x))
                .map(Rate::getRate);
    }

    public Flux<Float> getAllRate(){
        return rateRepository.findAll()
                .doOnNext(x-> log.info("before delay : {}", x.getFromCurrencyCode()))
                .flatMap(x->testResult(x).subscribeOn(Schedulers.boundedElastic()))
                .doOnNext(x-> log.info("after delay : {}", x.getFromCurrencyCode()))
                .map(Rate::getRate);
    }

    private Mono<Rate> testResult(Rate rate){

        Random random = new Random();

        return rateRepository.findById(rate.getFromCurrencyCode())
                .doOnNext(x->log.info("deplying element :{} ", rate.getFromCurrencyCode()))
                .delayElement(Duration.ofSeconds(random.nextInt(10)));

    }

    public Mono<Rate> addRate(Rate rate){
        return rateRepository.save(rate);
    }

}
