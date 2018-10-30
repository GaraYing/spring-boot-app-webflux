package com.gara.repository;

import com.gara.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: GaraYing
 * @create: 2018-10-30 10:52
 **/

//@Repository
public interface UserRepository extends ReactiveCrudRepository<User,String> {

    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}
