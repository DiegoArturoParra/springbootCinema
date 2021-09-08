package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.edu.cundi.cinema.DTOs.PeliculasDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.services.interfaces.ICRUD;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AutorService")
public class AutorService implements ICRUD<Autor> {
    private RespuestaDTO respuesta = new RespuestaDTO();

    @Override
    public RespuestaDTO getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO getById(Integer Id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO create(Autor entidad) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO edit(Autor entidad) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO delete(Integer Id) {
        // TODO Auto-generated method stub
        return null;
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

}
