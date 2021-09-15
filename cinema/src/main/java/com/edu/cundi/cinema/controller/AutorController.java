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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autores/")
@Validated
public class AutorController {
    @Autowired
    @Qualifier("AutorService")
    private ICRUD<Autor> service;
    @ApiOperation(value = "Busca Un autor por su nombre",response = Autor.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Autor encontrado"),
        @ApiResponse(code = 401, message = "No tienes autorizacion para ver este autor"),
        @ApiResponse(code = 403, message = "Está prohibido acceder al recurso al que intentaba acceder"),
        @ApiResponse(code = 404, message = "Autor No encontrado")
    })
    @GetMapping(value = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaDTO> getAutor(@PathVariable @Size(min = 4) @NotBlank String nombre) {

        return ResponseEntity.ok(service.getByNombre(nombre));
    }

}
