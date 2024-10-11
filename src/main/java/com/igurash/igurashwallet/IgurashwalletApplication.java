package com.igurash.igurashwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IgurashwalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgurashwalletApplication.class, args);
	}

}
