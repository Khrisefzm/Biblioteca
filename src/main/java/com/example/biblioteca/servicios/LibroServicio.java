package com.example.biblioteca.servicios;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.entidades.Libro;
import com.example.biblioteca.repositorios.AutorRepositorio;
import com.example.biblioteca.repositorios.EditorialRepositorio;
import com.example.biblioteca.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial) {
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        Autor autor = autorRepositorio.findById(idAutor).get();
        libro.setAutor(autor);

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }
    
}
