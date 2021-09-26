package com.edu.cundi.cinema;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.AutorDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.repository.IAutorRepository;
import com.edu.cundi.cinema.repository.IPeliculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	private final IPeliculaRepository _peliculaRepository;
	private final IAutorRepository _autorRepository;

	@Autowired
	public CinemaApplication(IPeliculaRepository peliculaRepository, IAutorRepository autorRepository) {
		_peliculaRepository = peliculaRepository;
		_autorRepository = autorRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	private void SeedData() {
		List<Pelicula> listapeliculas = new ArrayList<Pelicula>();
		Autor autorTerminator = new Autor("james", "cameron", 67, "39223873");
		if (_autorRepository.findAll().isEmpty()) {
			_autorRepository.insert(autorTerminator);
		}
		Optional<Autor> autor = _autorRepository.getAutorByCedula(autorTerminator.getCedula());
		if (autor.isPresent()) {
			if (_peliculaRepository.findAll().isEmpty()) {
				AutorDTO autorId = new AutorDTO(autor.get().getId());
				listapeliculas.add(new Pelicula("The Terminator", autorId, LocalDate.of(1984, Month.OCTOBER, 20),
				"En el año 2029, después de devastar la Tierra y esclavizar a la humanidad, las máquinas,"
						+ "gobernadas por la inteligencia artificial conocida como Skynet, están a punto de"
						+ "perder la guerra contra la resistencia humana liderada por John Connor."));

				listapeliculas.add(new Pelicula("El juicio final", autorId, LocalDate.of(1991, Month.JULY, 1),
			    "sigue la historia de Sarah Connor (Hamilton) y su hijo de diez años John (Furlong) mientras son perseguidos por un T-1000 (Patrick),"
						+ "un Terminator nuevo y más avanzado que el de la anterior película; hecho de metal líquido y capaz de cambiar de forma"));

				_peliculaRepository.insert(listapeliculas);
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {
		SeedData();
	}
}
