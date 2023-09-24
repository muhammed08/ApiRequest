package com.ameen.ApiRequest.repository;

import com.ameen.ApiRequest.model.Rate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface RateRepository extends ReactiveCrudRepository<Rate, String> {
}
