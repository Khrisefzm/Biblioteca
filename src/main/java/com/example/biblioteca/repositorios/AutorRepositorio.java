package com.example.biblioteca.repositorios;

import org.springframework.stereotype.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, UUID> {
    
}
