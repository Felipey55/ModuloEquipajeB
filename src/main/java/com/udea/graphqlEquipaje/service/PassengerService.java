package com.udea.graphqlEquipaje.service;

import com.udea.graphqlEquipaje.entity.Passenger;
import com.udea.graphqlEquipaje.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public Passenger addPassenger(String firstName, String lastName, LocalDate birthDate, String nationality, String passportNumber, String email, String phone) {
        Passenger passenger = new Passenger(null, firstName, lastName, birthDate, nationality, passportNumber, email, phone, null);
        return passengerRepository.save(passenger);
    }
}
