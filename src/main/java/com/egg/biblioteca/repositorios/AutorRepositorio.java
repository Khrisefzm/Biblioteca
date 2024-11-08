package com.egg.biblioteca.repositorios;

import org.springframework.stereotype.Repository;

import com.egg.biblioteca.entidades.Autor;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, UUID> {
    
}
