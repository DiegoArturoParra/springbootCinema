package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.edu.cundi.cinema.DTOs.PeliculasDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorRepository;
import com.edu.cundi.cinema.services.interfaces.ICRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AutorService")
public class AutorService implements ICRUD<Autor> {

    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private IAutorRepository _autorRepository;

    @Override
    public RespuestaDTO getAll() {
        List<Autor> autores = _autorRepository.findAll();
        if (autores.size() == 0) {
            respuesta.setMensaje("no hay autores");
        } else {
            respuesta.setMensaje("hay: " + autores.size());
        }
        respuesta.setData(autores);
        return respuesta;
    }

    @Override
    public RespuestaDTO getById(String Id) throws ModelNotFoundException {
        Optional<Autor> autor = _autorRepository.findById(Id);
        if (!autor.isPresent()) {
            throw new ModelNotFoundException("Este autor no existe.");
        }
        respuesta.setData(autor.get());
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Autor entidad) throws ConflictException {
        existeCedula(entidad, false);
        _autorRepository.insert(entidad);
        respuesta.setMensaje("creado");
        return respuesta;
    }

    public void existeCedula(Autor autor, boolean editOrCreate) throws ConflictException {
        Optional<Autor> existe = _autorRepository.getCedula(autor.getCedula());
        if (existe.isPresent()) {
            throw new ConflictException("La cedula ya existe digite otra.");
        }
        if (editOrCreate) {
            if (existe.isPresent() && existe.get().getId() != autor.getId()) {
                throw new ConflictException("La cedula ya existe digite otra.");
            }
        }
    }

    @Override
    public RespuestaDTO edit(Autor entidad) throws ConflictException {
        existeCedula(entidad, true);
        _autorRepository.save(entidad);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO getByNombre(String nombre) {
        List<Pelicula> encontrado = PeliculasDTO.listapeliculas.stream()
                .filter(x -> nombre.equals(x.getAutor().getNombre())).collect(Collectors.toList());
        if (encontrado.isEmpty()) {
            return null;
        }
        Pelicula pelicula = encontrado.get(0);
        Autor autor = pelicula.getAutor();
        respuesta.setData(autor);
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(String Id) {
        // TODO Auto-generated method stub
        return null;
    }

}
