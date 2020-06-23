package com.chan.delaywork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class DelayworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayworkApplication.class, args);
    }

}
