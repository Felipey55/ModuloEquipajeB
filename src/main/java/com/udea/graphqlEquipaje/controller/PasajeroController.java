package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Reservation;
import com.udea.graphqlEquipaje.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class EquipajeController {

    private final EquipajeRepository equipajeRepository;

    public EquipajeController(EquipajeRepository equipajeRepository) {
        this.equipajeRepository = equipajeRepository;
    }

    @QueryMapping
    public List<Equipaje> todosLosEquipajes() {
        return equipajeRepository.findAll();
    }

    @QueryMapping
    public Equipaje equipajePorId(@Argument Long id) {
        return equipajeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipaje no encontrado"));
    }

    @MutationMapping
    public Equipaje agregarEquipaje(
            @Argument Float alto,
            @Argument Float largo,
            @Argument Float ancho,
            @Argument Float peso,
            @Argument String tipo,
            @Argument String ubicacion,
            @Argument Float valor
    ) {
        Equipaje equipaje = new Equipaje();
        equipaje.setAlto(alto);
        equipaje.setLargo(largo);
        equipaje.setAncho(ancho);
        equipaje.setPeso(peso);
        equipaje.setTipo(tipo);
        equipaje.setUbicacion(ubicacion);
        equipaje.setValor(valor);

        return equipajeRepository.save(equipaje);
    }

    @MutationMapping
    public Equipaje eliminarEquipaje(@Argument Long id) {
        Equipaje equipaje = equipajeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipaje no encontrado con ID: " + id));
        equipajeRepository.deleteById(id);
        return equipaje;
    }

    @MutationMapping
    public Equipaje actualizarEquipaje(
            @Argument Long id,
            @Argument Float alto,
            @Argument Float largo,
            @Argument Float ancho,
            @Argument Float peso,
            @Argument String tipo,
            @Argument String ubicacion,
            @Argument Float valor
    ) {
        Equipaje equipaje = equipajeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipaje no encontrado con ID: " + id));

        equipaje.setAlto(alto);
        equipaje.setLargo(largo);
        equipaje.setAncho(ancho);
        equipaje.setPeso(peso);
        equipaje.setTipo(tipo);
        equipaje.setUbicacion(ubicacion);
        equipaje.setValor(valor);

        return equipajeRepository.save(equipaje);
    }
}
