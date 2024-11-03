package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Vuelo;
import com.udea.graphqlEquipaje.repository.VueloRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VueloController {

    private final VueloRepository vueloRepository;

    public VueloController(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @QueryMapping
    public List<Vuelo> todosLosVuelos() {
        return vueloRepository.findAll();
    }

    @QueryMapping
    public Vuelo vueloPorId(@Argument Long id) {
        return vueloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));
    }

    @MutationMapping
    public Vuelo agregarVuelo(
            @Argument String numeroVuelo,
            @Argument String origen,
            @Argument String destino,
            @Argument String fechaSalida
    ) {
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo(numeroVuelo);
        vuelo.setOrigen(origen);
        vuelo.setDestino(destino);
        vuelo.setFechaSalida(fechaSalida);
        return vueloRepository.save(vuelo);
    }

    @MutationMapping
    public Vuelo eliminarVuelo(@Argument Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado con ID: " + id));
        vueloRepository.delete(vuelo);
        return vuelo;
    }
}