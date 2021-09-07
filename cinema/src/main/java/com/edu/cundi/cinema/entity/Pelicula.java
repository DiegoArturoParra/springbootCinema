package com.edu.cundi.cinema.entity;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Pelicula {
    @NotNull
    @Min(1)
    private int id;
    @NotBlank
    @Size(min = 5, max = 50)
    private String nombre;
    @NotNull
    @Valid
    private Autor autor;
    @Past
    private LocalDate fechaDeSalida;
    @NotBlank
    @Size(min = 10, max = 500)
    private String reseñaDePelicula;

    public Pelicula(int id, String nombre, Autor autor, LocalDate fechaDeSalida, String reseñaDePelicula) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaDeSalida = fechaDeSalida;
        this.reseñaDePelicula = reseñaDePelicula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeSalida() {
        return fechaDeSalida;
    }

    public void setFechaDeSalida(LocalDate fechaDeSalida) {
        this.fechaDeSalida = fechaDeSalida;
    }

    public String getReseñaDePelicula() {
        return reseñaDePelicula;
    }

    public void setReseñaDePelicula(String reseñaDePelicula) {
        this.reseñaDePelicula = reseñaDePelicula;
    }
}
