package com.edu.cundi.cinema.repository;

import java.util.Optional;

import com.edu.cundi.cinema.entity.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByCedula(String cedula);

    public Boolean existsByCedula(String cedula);

    public Boolean existsByCorreo(String correo);
}
