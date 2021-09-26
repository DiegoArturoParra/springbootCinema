package com.edu.cundi.cinema.DTOs;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.entity.Pelicula;

public class PeliculasDTO {
        public static final List<Pelicula> listapeliculas = new ArrayList<Pelicula>();
        private static Autor autorTerminator = new Autor("james", "cameron", 67,"39223873");

        static {
                listapeliculas.add(new Pelicula("The Terminator", autorTerminator,
                                LocalDate.of(1984, Month.OCTOBER, 20),
                                "En el año 2029, después de devastar la Tierra y esclavizar a la humanidad, las máquinas,"
                                                + "gobernadas por la inteligencia artificial conocida como Skynet, están a punto de"
                                                + "perder la guerra contra la resistencia humana liderada por John Connor."));

                listapeliculas.add(new Pelicula("El juicio final", autorTerminator,
                                LocalDate.of(1991, Month.JULY, 1),
                                "sigue la historia de Sarah Connor (Hamilton) y su hijo de diez años John (Furlong) mientras son perseguidos por un T-1000 (Patrick),"
                                                + "un Terminator nuevo y más avanzado que el de la anterior película; hecho de metal líquido y capaz de cambiar de forma"));
        }
}
