package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Baggage;
import com.udea.graphqlEquipaje.service.BaggageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BaggageController {

    private final BaggageService baggageService;

    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @QueryMapping
    public List<Baggage> allBaggage() {
        return baggageService.getAllBaggage();
    }

    @QueryMapping
    public Baggage baggageById(@Argument Long id) {
        return baggageService.getBaggageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Baggage not found"));
    }

    @QueryMapping
    public List<Baggage> baggageByReservationId(@Argument Long reservationId) {
        return baggageService.getBaggageByReservationId(reservationId);
    }

    // Agregar la mutación para añadir un nuevo equipaje
    @MutationMapping
    public Baggage addBaggage(
            @Argument Long reservationId,
            @Argument String baggageType,
            @Argument String baggageLocation,
            @Argument String description,
            @Argument Float basePrice,
            @Argument Float additionalCharge,
            @Argument Float dimensions,
            @Argument Float weight,
            @Argument int quantity,
            @Argument Float totalCost
    ) {
        return baggageService.addBaggage(reservationId, baggageType, baggageLocation, description, basePrice, additionalCharge, dimensions, weight, quantity, totalCost);
    }
}
