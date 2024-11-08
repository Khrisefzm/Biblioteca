package com.egg.biblioteca.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

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
  public String registrar(ModelMap model) {
    List<Autor> autores = autorServicio.listarAutores();
    List<Editorial> editoriales = editorialServicio.listarEditoriales();

    model.addAttribute("autores", autores);
    model.addAttribute("editoriales", editoriales);
    
    return "libro_form.html";
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    List<Libro> libros = libroServicio.listarLibros();
    model.addAttribute("libros", libros);
    return "libro_list.html";
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
