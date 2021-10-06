package com.edu.cundi.cinema.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "autores")
public class Autor {
    @Id
    private String id;
    @Field
    @NotBlank
    @ApiModelProperty(notes = "Nombre del autor", required = true)
    @Size(min = 4, max = 50)
    private String nombre;
    @NotBlank
    @ApiModelProperty(notes = "Apellido del autor", required = true)
    @Size(min = 4, max = 50)
    private String apellido;

    @ApiModelProperty(notes = "cédula del autor", required = true)
    @Size(min = 8, max = 50)
    private String cedula;

    @Min(value = 18, message = "Minimo de años 18")
    @Max(value = 90, message = "Maximo de años 90")
    @ApiModelProperty(notes = "Edad del autor", required = true)
    private int edad;

    
    public Autor(String id) {
        this.id = id;
    }


    public Autor(String nombre, String apellido, int edad, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cedula = cedula;
    }
}
