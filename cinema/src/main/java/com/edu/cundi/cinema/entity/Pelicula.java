package com.edu.cundi.cinema.entity;

import java.time.LocalDate;

public class Pelicula {
    private String nombre;
    private Autor autor;
    private LocalDate fechaDeSalida;
    private String reseñaDePelicula;
    
   
    public Pelicula(String nombre, Autor autor, LocalDate fechaDeSalida, String reseñaDePelicula) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaDeSalida = fechaDeSalida;
        this.reseñaDePelicula = reseñaDePelicula;
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
