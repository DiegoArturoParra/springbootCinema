package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.Optional;

import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.edu.cundi.cinema.DTOs.AutorDTO;
import com.edu.cundi.cinema.DTOs.AutorIdModel;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.controller.AutorController;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorRepository;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
@Service
@Qualifier("AutorService")
public class AutorService implements ICRUD<Autor> {

    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private IAutorRepository _autorRepository;
    private ModelMapper _mapper = new ModelMapper();

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        List<Autor> autores = _autorRepository.findAll();
        if (autores.size() == 0) {
            respuesta.setMensaje("no hay autores");
        } else {
            respuesta.setMensaje("hay: " + autores.size());
        }
        List<AutorDTO> autoresDTOs = (List<AutorDTO>) _mapper.map(autores, new TypeToken<List<AutorDTO>>() {
        }.getType());

        for (AutorDTO autorDTO : autoresDTOs) {
            autorDTO.add(LinkAutor(autorDTO.getId()));
        }
        respuesta.setData((autoresDTOs));
        return respuesta;
    }

    private Autor getAutorById(Integer Id) throws ModelNotFoundException {
        Optional<Autor> autor = _autorRepository.findById(Id);
        if (!autor.isPresent()) {
            throw new ModelNotFoundException("Este autor no existe.");
        }
        return autor.get();
    }

    @Override
    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
        respuesta.setData(getAutorById(Id));
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Autor entidad) throws ConflictException, ModelNotFoundException {
        existeCedula(entidad, false);
        Autor autor = _autorRepository.save(entidad);
        AutorIdModel autorDto = new AutorIdModel();
        autorDto.setId(autor.getId());
        autorDto.add(LinkAutor(autor.getId()));
        respuesta.setMensaje("creado");
        respuesta.setData(autorDto);
        return respuesta;
    }

    public Link LinkAutor(Integer Id) throws ModelNotFoundException {
        return linkTo(methodOn(AutorController.class).getAutor(Id)).withRel("Autor");
    }

    public void existeCedula(Autor autor, boolean editOrCreate) throws ConflictException {
        Optional<Autor> existe = _autorRepository.findByCedula(autor.getCedula());
        if (_autorRepository.existsByCedula(autor.getCedula())) {
            throw new ConflictException("La cedula ya existe digite otra.");
        }
        if (editOrCreate) {
            if (existe.isPresent() && existe.get().getId() != autor.getId()) {
                throw new ConflictException("La cedula ya existe digite otra.");
            }
        }
    }

    @Override
    public RespuestaDTO edit(Autor entidad) throws ConflictException, ModelNotFoundException {
        getById(entidad.getId());
        existeCedula(entidad, true);
        _autorRepository.save(entidad);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO getByNombre(String nombre) {
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
        getAutorById(Id);
        _autorRepository.deleteById(Id);
        respuesta.setMensaje("se ha eliminado.");
        return respuesta;
    }

    @Override
    public Page<Autor> getPaginado(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC));
        return _autorRepository.findAll(pageable);
    }
}
