package com.edu.cundi.cinema.controller;

import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/peliculas/")
@Validated
public class PeliculaController {

    @Autowired
    @Qualifier("PeliculaService")
    private ICRUD<Pelicula> service;
    @ApiOperation(value = "Busca Una pelicula por id",response = Pelicula.class)
    @RequestMapping(value = "/peliculas/{id}", method= RequestMethod.GET, produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pelicula Encontrada"),
        @ApiResponse(code = 401, message = "No tienes autorizacion para ver esta pelicula"),
        @ApiResponse(code = 403, message = "Está prohibido acceder a esta pelicula"),
        @ApiResponse(code = 404, message = "Pelicula no encontrada")
    })
    @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaDTO> getPelicula(@PathVariable @Min(1) @NotNull Integer Id) {

        return ResponseEntity.ok(service.getById(Id));
    }

    @ApiOperation(value = "Obtiene un listado de peliculas",response = Pelicula.class)
    @RequestMapping(value = "/listado", method= RequestMethod.GET, produces = "application/json")
    @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Peliculas Encontradas"),
        @ApiResponse(code = 404, message = "Peliculas no encontradas")
    })
    public ResponseEntity<RespuestaDTO> getPeliculas() {
        return ResponseEntity.ok(service.getAll());
    }


    @ApiOperation(value = "Crear Pelicula")
    @RequestMapping(value = "/crear", method = RequestMethod.POST, produces = "application/json")
    @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pelicula Creada"),
        @ApiResponse(code = 415, message = "Formato no valido")
    
    })
    public ResponseEntity<?> CrearPelicula(@Valid @RequestBody Pelicula entity) {
        service.create(entity);
        URI location = URI.create(String.format("/peliculas/%d", entity.getId()));
        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "Editar una pelicula")
    @RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT, produces = "application/json")
    @PutMapping(value = "editar/{id}")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pelicula Editada"),
        @ApiResponse(code = 415, message = "Formato no valido"),
        @ApiResponse(code = 404, message = "Pelicula no encontrada"),
        @ApiResponse(code = 400, message = "Url Erronea")
    
    }) 
    public ResponseEntity<RespuestaDTO> EditarPelicula(@PathVariable @Min(1) @NotNull int id,
            @Valid @RequestBody Pelicula entity) {
        return ResponseEntity.ok(service.edit(entity));
    }


    @ApiOperation(value = "Eliminar Pelicula")
    @RequestMapping(value="/eliminar/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Pelicula Editada"),
        @ApiResponse(code = 404, message = "Pelicula no encontrada")
    })
    @DeleteMapping(value = "eliminar/{Id}")
    public ResponseEntity<?> EliminarPelicula(@PathVariable @Min(1) @NotNull int Id) {
        service.delete(Id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
