package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Passenger;
import com.udea.graphqlEquipaje.service.PassengerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @QueryMapping
    public List<Passenger> allPassengers() {
        return passengerService.getAllPassengers();
    }

    @QueryMapping
    public Passenger passengerById(@Argument Long id) {
        return passengerService.getPassengerById(id);
    }

    @MutationMapping
    public Passenger addPassenger(@Argument String firstName, @Argument String lastName, @Argument String birthDate,
                                  @Argument String nationality, @Argument String passportNumber,
                                  @Argument String email, @Argument String phone) {
        LocalDate birth = LocalDate.parse(birthDate);
        return passengerService.addPassenger(firstName, lastName, birth, nationality, passportNumber, email, phone);
    }
}
