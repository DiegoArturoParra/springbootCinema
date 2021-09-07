package com.edu.cundi.cinema.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Autor {
    @NotBlank
    @Size(min = 4, max = 50)
    private String nombre;
    @NotBlank
    @Size(min = 4, max = 50)
    private String apellido;
    @Min(18)
    private int edad;

    public Autor() {
    }

    public Autor(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
