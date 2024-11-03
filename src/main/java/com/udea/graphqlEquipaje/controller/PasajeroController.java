package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Pasajero;
import com.udea.graphqlEquipaje.repository.PasajeroRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PasajeroController {

    private final PasajeroRepository pasajeroRepository;

    public PasajeroController(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    @QueryMapping
    public List<Pasajero> todosLosPasajeros() {
        return pasajeroRepository.findAll();
    }

    @QueryMapping
    public Pasajero pasajeroPorId(@Argument Long id) {
        return pasajeroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));
    }

    @MutationMapping
    public Pasajero agregarPasajero(
            @Argument String nombre,
            @Argument String apellido,
            @Argument String numeroPasaporte,
            @Argument String nacionalidad
    ) {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(nombre);
        pasajero.setApellido(apellido);
        pasajero.setNumeroPasaporte(numeroPasaporte);
        pasajero.setNacionalidad(nacionalidad);
        return pasajeroRepository.save(pasajero);
    }

    @MutationMapping
    public Pasajero eliminarPasajero(@Argument Long id) {
        Pasajero pasajero = pasajeroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado con ID: " + id));
        pasajeroRepository.delete(pasajero);
        return pasajero;
    }
}