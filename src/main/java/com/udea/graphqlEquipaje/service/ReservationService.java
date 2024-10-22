package com.udea.graphqlEquipaje.service;

import com.udea.graphqlEquipaje.entity.Passenger;
import com.udea.graphqlEquipaje.entity.Reservation;
import com.udea.graphqlEquipaje.repository.PassengerRepository;
import com.udea.graphqlEquipaje.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PassengerRepository passengerRepository;

    public ReservationService(ReservationRepository reservationRepository, PassengerRepository passengerRepository) {
        this.reservationRepository = reservationRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation makeReservation(Long passengerId, LocalDate reservationDate, LocalTime reservationTime,
                                       String originCity, String destinationCity, LocalDate departureDate, LocalDate returnDate,
                                       String seat) {

        // Verificar que el pasajero existe
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        // Crear la reserva
        Reservation reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setReservationDate(reservationDate);
        reservation.setReservationTime(reservationTime);
        reservation.setOriginCity(originCity);
        reservation.setDestinationCity(destinationCity);
        reservation.setDepartureDate(departureDate);
        reservation.setReturnDate(returnDate);
        reservation.setSeat(seat);

        // Generar un código de reserva único
        String reservationCode = generateReservationCode();
        reservation.setReservationCode(reservationCode);

        return reservationRepository.save(reservation);
    }

    // Método para generar un código único de reserva
    private String generateReservationCode() {
        return "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
