package de.codefor.le.locations.locator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAsync
@SpringBootApplication
@EnableWebMvc
public class LocationsLocator {

    public static void main(final String[] args) {
        SpringApplication.run(LocationsLocator.class, args);
    }
}
