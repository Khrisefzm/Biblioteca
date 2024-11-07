package com.example.biblioteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
  
  @Autowired
  private EditorialServicio editorialServicio;

  @GetMapping("/registrar")
  public String registrar() {
    return "editorial_form.html";
  }

  @PostMapping("/registro")
  public String registro(@RequestParam String nombre, ModelMap model) {
    try {
      editorialServicio.crearEditorial(nombre);
      model.put("exito", "Editorial creado con exito");
      return "index.html";
    } catch (MiException ex) {
      model.put("error", ex.getMessage());
      return "editorial_form.html";
    }
  }

}
