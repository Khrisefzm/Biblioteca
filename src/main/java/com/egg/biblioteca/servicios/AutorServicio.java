package com.egg.biblioteca.servicios;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;


@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();// Instancio un objeto del tipo Autor
        autor.setNombre(nombre);// Seteo el atributo, con el valor recibido como parámetro
        autorRepositorio.save(autor); // Persisto el dato en mi BBDD
    }


    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional(readOnly = true)
    public Autor getOne(UUID id){
        return autorRepositorio.getReferenceById(id);
    }

    @Transactional
    public void modificarAutor(UUID id, String nombreNuevo)throws MiException {
        validar(nombreNuevo);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombreNuevo);
            autorRepositorio.save(autor);
        }
        else{
            System.out.println("No se encontro el autor con el id: " + id);
        }
    }

    private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
    }
}

