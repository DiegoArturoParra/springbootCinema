package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.PeliculaDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.controller.AutorController;
import com.edu.cundi.cinema.controller.PeliculaController;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IPeliculaRepository;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Qualifier("PeliculaService")
public class PeliculaService implements ICRUD<Pelicula> {
    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private IPeliculaRepository _PeliculaRepository;
    @Autowired
    private AutorService autorService;
    private ModelMapper _mapper = new ModelMapper();

    PeliculaService() {

    }

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        List<Pelicula> listado = _PeliculaRepository.findAll();
        if (listado.size() == 0) {
            respuesta.setMensaje("no hay peliculas.");
        } else {
            respuesta.setMensaje("hay " + listado.size());
        }

        List<PeliculaDTO> peliculasDTOs = (List<PeliculaDTO>) _mapper.map(listado, new TypeToken<List<PeliculaDTO>>() {
        }.getType());

        for (PeliculaDTO pelicula : peliculasDTOs) {
            pelicula.getAutor().add(linkTo(methodOn(AutorController.class).getAutor(pelicula.getAutor().getId()))
                    .withRel("Autor de la pelicula"));
            pelicula.add(linkTo(methodOn(PeliculaController.class).getPelicula(pelicula.getId())).withRel("Pelicula"));
        }

        respuesta.setData(peliculasDTOs);
        return respuesta;
    }

    private Pelicula getPeliculaById(String Id) throws ModelNotFoundException {
        Optional<Pelicula> pelicula = _PeliculaRepository.findById(Id);
        if (!pelicula.isPresent()) {
            throw new ModelNotFoundException("Esta pelicula no existe.");
        }
        return pelicula.get();
    }

    @Override
    public RespuestaDTO getById(String Id) throws ModelNotFoundException {
        respuesta.setData(getPeliculaById(Id));
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Pelicula entidad) throws ConflictException, ModelNotFoundException {
        autorService.getById(entidad.getAutor().getId()).getData();
        _PeliculaRepository.insert(entidad);
        respuesta.setMensaje("creado.");
        return respuesta;
    }

    @Override
    public RespuestaDTO edit(Pelicula entidad) throws ConflictException, ModelNotFoundException {
        autorService.getById(entidad.getAutor().getId()).getData();
        getPeliculaById(entidad.getId());
        _PeliculaRepository.save(entidad);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(String Id) throws ModelNotFoundException {
        getPeliculaById(Id);
        _PeliculaRepository.deleteById(Id);
        respuesta.setMensaje("se ha eliminado.");
        return respuesta;
    }

    @Override
    public RespuestaDTO getByNombre(String nombre) {

        return null;
    }

}
