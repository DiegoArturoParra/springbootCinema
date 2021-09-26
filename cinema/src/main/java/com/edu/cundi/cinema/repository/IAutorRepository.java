package com.edu.cundi.cinema.repository;

import java.util.Optional;

import com.edu.cundi.cinema.entity.Autor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends MongoRepository<Autor, String> {
    @Query("{ 'cedula' : ?0 }")
    Optional<Autor> getCedula(String cedula);
}
