package com.edu.cundi.cinema.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/autores/")
@Validated
public class AutorController {
        @Autowired
        @Qualifier("AutorService")
        private ICRUD<Autor> service;

        @ApiOperation(value = "Busca Un autor por su nombre", response = Autor.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autor encontrado"),
                        @ApiResponse(code = 401, message = "No tienes autorizacion para ver este autor"),
                        @ApiResponse(code = 403, message = "Está prohibido acceder al recurso al que intentaba acceder"),
                        @ApiResponse(code = 404, message = "Autor No encontrado") })
        @GetMapping(value = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<RespuestaDTO> getAutorByNombre(@Valid @PathVariable @Size(min = 4) @NotBlank String nombre) {

                return ResponseEntity.ok(service.getByNombre(nombre));
        }

        @ApiOperation(value = "Busca Una Autor por id", response = Autor.class)

        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Encontrada"),
                        @ApiResponse(code = 401, message = "No tienes autorizacion para ver esta Autor"),
                        @ApiResponse(code = 403, message = "Está prohibido acceder a esta Autor"),
                        @ApiResponse(code = 404, message = "Autor no encontrada") })
        @GetMapping(value = "buscar/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<RespuestaDTO> getAutor(
                        @ApiParam(name = "Codigo", value = "Codigo de la Autor", required = true) @Valid @PathVariable @NotNull @Size(min = 5) String Id)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getById(Id));
        }

        @ApiOperation(value = "Obtiene un listado de Autors", response = Autor.class)
        @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autors Encontradas"),
                        @ApiResponse(code = 404, message = "Autors no encontradas") })
        public ResponseEntity<RespuestaDTO> getAutors() {
                return ResponseEntity.ok(service.getAll());
        }

        @ApiOperation(value = "Crear Autor")
        @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Autor Creada"),
                        @ApiResponse(code = 415, message = "Formato no valido")

        })
        public ResponseEntity<RespuestaDTO> CrearAutor(@Valid @RequestBody Autor entity)
                        throws ConflictException, ModelNotFoundException {
                RespuestaDTO response = service.create(entity);
                URI location = URI.create(String.format("/autores/%s", entity.getId()));
                response.setData(location);
                return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Editar una Autor")
        @PutMapping(value = "editar")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Editada"),
                        @ApiResponse(code = 415, message = "Formato no valido"),
                        @ApiResponse(code = 404, message = "Autor no encontrada"),
                        @ApiResponse(code = 400, message = "Url Erronea")

        })
        public ResponseEntity<String> EditarAutor(@Valid @RequestBody Autor entity)
                        throws ConflictException, ModelNotFoundException {
                return ResponseEntity.ok(service.edit(entity).getMensaje());
        }

        @ApiOperation(value = "Eliminar autor")
        @ApiResponses(value = { @ApiResponse(code = 204, message = "Autor Editada"),
                        @ApiResponse(code = 404, message = "Autor no encontrada") })
        @DeleteMapping(value = "eliminar/{Id}")
        public ResponseEntity<?> EliminarAutor(
                        @ApiParam(name = "Codigo", value = "Codigo de la Autor", required = true) @Valid @PathVariable @Size(min = 4) @NotNull String Id)
                        throws ModelNotFoundException {
                service.delete(Id);
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
}
