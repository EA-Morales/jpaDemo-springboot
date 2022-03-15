package com.example.jpademo.controller;

import com.example.jpademo.model.Persona;
import com.example.jpademo.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService interPersona;

    @GetMapping ("/personas/traer")
    public List<Persona> getPersonas() {
        return interPersona.getPersonas();
    }

    @PostMapping ("/personas/crear")
    public String createStudent(@RequestBody Persona perso){
        interPersona.savePersona(perso);

        return "La persona fue creada correctamente";
    }

    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona (@PathVariable Long id) {
        interPersona.deletePersona(id);

        return "La persona fue eliminada correctamente";
    }

    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam ("nombre") String nuevoNombre,
                               @RequestParam ("apellido") String nuevoApellido,
                               @RequestParam ("edad") int nuevaEdad) {
        Persona perso = interPersona.findPersona(id);

        perso.setApellido(nuevoApellido);
        perso.setNombre(nuevoNombre);
        perso.setEdad(nuevaEdad);

        interPersona.savePersona(perso);

        return perso;
    }
}
