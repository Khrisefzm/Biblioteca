package com.egg.biblioteca.servicios;

import java.util.Date;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial) throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
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

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn) {
        return libroRepositorio.getReferenceById(isbn);
    }
    
    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial) throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        if (respuestaLibro.isPresent()) {
            Libro libro = respuestaLibro.get();

            // Buscar autor y editorial
            Optional<Autor> autorOptional = autorRepositorio.findById(idAutor);
            Optional<Editorial> editorialOptional = editorialRepositorio.findById(idEditorial);

            if (autorOptional.isPresent() && editorialOptional.isPresent()) {
                Autor autor = autorOptional.get();
                Editorial editorial = editorialOptional.get();

                // Actualizar libro
                libro.setTitulo(titulo);
                libro.setEjemplares(ejemplares);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setAlta(new Date());

                libroRepositorio.save(libro);  // Guardar cambios en el repositorio
            } else {
                throw new MiException("Autor o Editorial no encontrados");
            }
        } else {
            throw new MiException("Libro no encontrado con ISBN: " + isbn);
        }
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {
                
        if (isbn == null) {
            throw new MiException("el isbn no puede ser nulo");
        }
        List<Libro> libros= listarLibros();
        for (Libro libro : libros) {
            if (isbn.equals(libro.getIsbn())){
                throw new MiException("el isbn " + isbn + " ya existe, por favor use otro");
            }
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiException("ejemplares no puede ser nulo");
        }
        if (idAutor == null) {
            throw new MiException("el Autor no puede ser nulo o estar vacio");
        }

        if (idEditorial == null) {
            throw new MiException("La Editorial no puede ser nula o estar vacia");
        }
    }
}
