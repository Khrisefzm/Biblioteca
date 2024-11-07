package com.example.biblioteca.controladores;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.servicios.AutorServicio;
import com.example.biblioteca.servicios.EditorialServicio;
import com.example.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
  @Autowired
  private LibroServicio libroServicio;
  @Autowired
  private AutorServicio autorServicio;
  @Autowired
  private EditorialServicio editorialServicio;

  @GetMapping("/registrar")
  public String registrar() {
    return "libro_form.html";
  }

  @PostMapping("/registro")
  public String registro(@RequestParam(required = false) Long isbn ,@RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,@RequestParam UUID idAutor, @RequestParam UUID idEditorial) {
    try {
      libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
      return "index.html";
    } catch (MiException ex) {
      Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
      return "libro_form.html";
    }
  }
}
