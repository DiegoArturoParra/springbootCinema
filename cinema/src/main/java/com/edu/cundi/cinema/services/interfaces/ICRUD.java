package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;

public interface ICRUD<T> {
    public RespuestaDTO getAll();

    public RespuestaDTO getById(String Id) throws ModelNotFoundException;

    public RespuestaDTO getByNombre(String nombre);

    public RespuestaDTO create(T entidad) throws ConflictException;

    public RespuestaDTO edit(T entidad) throws ConflictException;;

    public RespuestaDTO delete(String Id) throws ModelNotFoundException;
}
