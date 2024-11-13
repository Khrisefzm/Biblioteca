package com.egg.biblioteca.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
  
  @Autowired
  private EditorialServicio editorialServicio;

  @GetMapping("/registrar")
  public String registrar() {
    return "editorial_form.html";
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    List<Editorial> editoriales = editorialServicio.listarEditoriales();
    model.addAttribute("editoriales", editoriales);
    return "editorial_list.html";
  }

  @GetMapping("/modificar/{id}")
  public String modificar(@PathVariable UUID id, ModelMap model) {
    model.put("editorial", editorialServicio.getOne(id));
    return "editorial_modificar.html";
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

  @PostMapping("{id}")
  public String modificar(@PathVariable UUID id, String nombre, ModelMap model){
    try {
      editorialServicio.modificarEditorial(id, nombre);
      model.put("exito", "Editorial modificado con exito");
      return "redirect:../editorial/lista";
    } catch (Exception e) {
      model.put("error", e.getMessage());
      return "editorial_modificar.html";
    }
  }
}
