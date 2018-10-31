package com.gara;

import com.gara.domain.User;
import com.gara.event.MyEvent;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.jnlp.UnavailableServiceException;
import javax.jws.soap.SOAPBinding;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    public void wenClientTest1() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8888");   // 1
        Mono<String> resp = webClient
                .get().uri("/hello/sayHi") // 2
                .retrieve() // 3
                .bodyToMono(String.class);  // 4
        resp.subscribe(System.out::println);    // 5
        TimeUnit.SECONDS.sleep(1);  // 6
    }

    @Test
    public void wevClientTest2() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8888").build();
        webClient
                .get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(User.class))
                .doOnNext(System.out::println)
                .blockLast();
    }

    @Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8888");
        webClient
                .get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)    // 1 配置请求Header：Content-Type: text/event-stream，即SSE；
                .retrieve()
                .bodyToFlux(String.class)
                .log()  // 2 这次用log()代替doOnNext(System.out::println)来查看每个元素；
                .take(10)   // 3 由于/times是一个无限流，这里取前10个，会导致流被取消；
                .blockLast();
    }

    @Test
    public void webClientTest4() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new MyEvent(System.currentTimeMillis(), "message-" + l)).take(5); // 1
        WebClient webClient = WebClient.create("http://localhost:8888");
        webClient
                .post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON) // 2
                .body(eventFlux, MyEvent.class) // 3
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
