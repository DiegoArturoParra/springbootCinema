package com.edu.cundi.cinema.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores/")
@Validated
public class AutorController {
    @Autowired
    @Qualifier("AutorService")
    private ICRUD<Autor> service;

    @GetMapping(value = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaDTO> getAutor(@PathVariable @Size(min = 4) @NotBlank String nombre) {

        return ResponseEntity.ok(service.getByNombre(nombre));
    }

}
