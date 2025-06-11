package org.evgeny.hairok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HairokApplication {

    public static void main(String[] args) {
        SpringApplication.run(HairokApplication.class, args);
        System.out.println("Привет кнут");
    }

}
