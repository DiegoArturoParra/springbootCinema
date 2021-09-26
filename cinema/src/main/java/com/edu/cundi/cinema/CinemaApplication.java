package com.edu.cundi.cinema;

import com.edu.cundi.cinema.DTOs.PeliculasDTO;
import com.edu.cundi.cinema.repository.IPeliculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	private final IPeliculaRepository repository;

	@Autowired
	public CinemaApplication(IPeliculaRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (repository.findAll().isEmpty()) {
			repository.insert(PeliculasDTO.listapeliculas);
		}
	}

}
