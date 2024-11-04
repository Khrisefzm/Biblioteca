package com.example.biblioteca.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();// Instancio un objeto del tipo Editorial
        editorial.setNombre(nombre);// Seteo el atributo, con el valor recibido como parámetro
        editorialRepositorio.save(editorial); // Persisto el dato en mi BBDD
    }
}
