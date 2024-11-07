package com.example.biblioteca.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
  public String registro(@RequestParam String nombre) {
    try {
      editorialServicio.crearEditorial(nombre);
      return "index.html";
    } catch (MiException ex) {
      Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
      return "editorial_form.html";
    }
  }

}
