package com.edu.cundi.cinema.services.implement;

import com.edu.cundi.cinema.DTOs.PeliculasDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.services.interfaces.ICRUD;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("PeliculaService")
public class PeliculaService implements ICRUD<Pelicula> {
    private RespuestaDTO respuesta = new RespuestaDTO();

    @Override
    public RespuestaDTO getAll() {
        respuesta.setData(PeliculasDTO.listapeliculas);
        respuesta.setMensaje("hay " + PeliculasDTO.listapeliculas.size());
        return respuesta;
    }

    @Override
    public RespuestaDTO getById(Integer Id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO create(Pelicula entidad) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO edit(Pelicula entidad) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO delete(Integer Id) {
        // TODO Auto-generated method stub
        return null;
    }

}
