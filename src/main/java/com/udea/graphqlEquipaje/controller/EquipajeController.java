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
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @QueryMapping
    public List<Reservation> allReservations() {
        return reservationService.getAllReservations();
    }

    @QueryMapping
    public Reservation reservationById(@Argument Long id) {
        return reservationService.getReservationById(id);
    }

    @MutationMapping
    public Reservation makeReservation(@Argument Long passengerId, @Argument String reservationDate,
                                       @Argument String reservationTime, @Argument String originCity,
                                       @Argument String destinationCity, @Argument String departureDate,
                                       @Argument String returnDate, @Argument String seat) {
        LocalDate resDate = LocalDate.parse(reservationDate);
        LocalTime resTime = LocalTime.parse(reservationTime);
        LocalDate depDate = LocalDate.parse(departureDate);
        LocalDate retDate = returnDate != null ? LocalDate.parse(returnDate) : null;

        return reservationService.makeReservation(passengerId, resDate, resTime, originCity, destinationCity, depDate, retDate, seat);
    }
}
