package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IPeliculaRepository;
import com.edu.cundi.cinema.services.interfaces.ICRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("PeliculaService")
public class PeliculaService implements ICRUD<Pelicula> {
    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private IPeliculaRepository _PeliculaRepository;

    private AutorService autorService = new AutorService();

    PeliculaService() {

    }

    @Override
    public RespuestaDTO getAll() {
        List<Pelicula> listado = _PeliculaRepository.findAll();
        if (listado.size() == 0) {
            respuesta.setMensaje("no hay peliculas.");
        } else {
            respuesta.setMensaje("hay " + listado.size());
        }
        respuesta.setData(listado);
        return respuesta;
    }

    private Optional<Pelicula> getPeliculaById(String Id) throws ModelNotFoundException {
        Optional<Pelicula> pelicula = _PeliculaRepository.findById(Id);
        if (!pelicula.isPresent()) {
            throw new ModelNotFoundException("Esta pelicula no existe.");
        }
        return pelicula;
    }

    @Override
    public RespuestaDTO getById(String Id) throws ModelNotFoundException {
        Optional<Pelicula> pelicula = getPeliculaById(Id);
        respuesta.setData(pelicula.get());
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Pelicula entidad) throws ConflictException {
        autorService.create(entidad.getAutor());
        _PeliculaRepository.insert(entidad);
        respuesta.setMensaje("creado.");
        return respuesta;
    }

    @Override
    public RespuestaDTO edit(Pelicula entidad) throws ConflictException {
        autorService.edit(entidad.getAutor());
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
