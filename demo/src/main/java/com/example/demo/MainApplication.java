package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.MusicLoad;
import com.example.demo.model.MusicRepository;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	ApplicationRunner init(MusicRepository repository) {
		return args -> {

			repository.save(new MusicLoad("YOU BELONG WITH ME",
					"Taylor Swift", "Country"));
			repository.save(new MusicLoad("BEST SONG EVER", 
					"One Direction", "Pop"));
			repository.save(new MusicLoad("LOVE IN THE RAIN",
					"Rihanna", "RnB"));
			repository.findAll().forEach(System.out::println);
		};
	}
}

