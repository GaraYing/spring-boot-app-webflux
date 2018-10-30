package com.gara.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description: 简单的配置实现了 命令式的,同步阻塞的[spring +webmvc + ServletAPI + Tomcat]
 *                        ==>  响应式的,异步非阻塞[spring + webflux + Netty]
 * @author: GaraYing
 * @create: 2018-10-30 14:26
 **/

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/sayHi")
    public Mono<String> hello() {   // 【改】返回类型为Mono<String>
        return Mono.just("hi,Welcome to reactive world ~");     // 【改】使用Mono.just生成响应式数据
    }
}
