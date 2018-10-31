package com.gara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    from site https://blog.csdn.net/get_set/article/details/79480233
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.run(args);
    }
}
