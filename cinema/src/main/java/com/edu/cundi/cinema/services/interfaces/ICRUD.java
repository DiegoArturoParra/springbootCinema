package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import org.springframework.data.domain.Page;

public interface ICRUD<T> {
    public RespuestaDTO getAll() throws ModelNotFoundException;

    public Page<T> getPaginado(int page, int pageSize);

    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException;

    public RespuestaDTO getByNombre(String nombre);

    public RespuestaDTO create(T entidad) throws ConflictException, ModelNotFoundException;

    public RespuestaDTO edit(T entidad) throws ConflictException, ModelNotFoundException;

    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException;
}
