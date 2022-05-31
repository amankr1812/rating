package com.origami.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.origami.rating.property.*;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class RatingApplication {

	public static void main(String[] args) {
        SpringApplication.run(RatingApplication.class, args);
    }

}
