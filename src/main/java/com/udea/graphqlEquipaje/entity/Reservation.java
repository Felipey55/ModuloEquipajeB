package com.udea.graphqlEquipaje.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String originCity;
    private String destinationCity;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String seat;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Baggage> baggage;

    // Agregar el campo reservationCode
    private String reservationCode;
}
