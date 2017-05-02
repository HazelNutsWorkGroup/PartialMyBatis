package com.taiji;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Sleeb on 2017/5/2.
 */
@SpringBootApplication
public class MicroServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MicroServiceApplication.class).web(true).run(args);
    }
}

