package com.example.noqbites;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class NoQBitesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoQBitesApplication.class, args);
	}
}