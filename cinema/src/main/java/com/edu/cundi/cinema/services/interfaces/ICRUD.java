package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;

public interface ICRUD<T> {
    public RespuestaDTO getAll();

    public RespuestaDTO getById(Integer Id);

    public RespuestaDTO getByNombre(String nombre);

    public RespuestaDTO create(T entidad);

    public RespuestaDTO edit(T entidad);

    public RespuestaDTO delete(Integer Id);
}
