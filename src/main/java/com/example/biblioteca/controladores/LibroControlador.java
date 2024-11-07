package com.example.biblioteca.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.biblioteca.excepciones.MiException;
// import com.example.biblioteca.servicios.AutorServicio;
// import com.example.biblioteca.servicios.EditorialServicio;
import com.example.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
  @Autowired
  private LibroServicio libroServicio;
  // @Autowired
  // private AutorServicio autorServicio;
  // @Autowired
  // private EditorialServicio editorialServicio;

  @GetMapping("/registrar")
  public String registrar() {
    return "libro_form.html";
  }

  @PostMapping("/registro")
  public String registro(@RequestParam(required = false) Long isbn ,@RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,@RequestParam UUID idAutor, @RequestParam UUID idEditorial, ModelMap modelo) {
    try {
      libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
      modelo.put("exito", "Libro creado con exito");
      return "index.html";
    } catch (MiException ex) {
      modelo.put("error", ex.getMessage());
      return "libro_form.html";
    }
  }
}
