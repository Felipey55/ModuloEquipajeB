package com.udea.graphqlEquipaje.service;

import com.udea.graphqlEquipaje.entity.Baggage;
import com.udea.graphqlEquipaje.entity.Reservation;
import com.udea.graphqlEquipaje.repository.BaggageRepository;
import com.udea.graphqlEquipaje.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BaggageService {

    private static final Logger logger = LoggerFactory.getLogger(BaggageService.class);

    private final BaggageRepository baggageRepository;
    private final ReservationRepository reservationRepository;

    public BaggageService(BaggageRepository baggageRepository, ReservationRepository reservationRepository) {
        this.baggageRepository = baggageRepository;
        this.reservationRepository = reservationRepository;
    }

    // Método para añadir equipaje
    public Baggage addBaggage(Long reservationId, String baggageType, String baggageLocation, String description,
                              Float basePrice, Float additionalCharge, Float dimensions, Float weight, int quantity, Float totalCost) {

        Baggage baggage = new Baggage();
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        reservation.ifPresent(baggage::setReservation);

        baggage.setBaggageType(baggageType);
        baggage.setBaggageLocation(baggageLocation);
        baggage.setDescription(description);
        baggage.setBasePrice(basePrice);
        baggage.setAdditionalCharge(additionalCharge);
        baggage.setDimensions(dimensions);
        baggage.setWeight(weight);
        baggage.setQuantity(quantity);
        baggage.setTotalCost(totalCost);

        return baggageRepository.save(baggage);
    }

    // Método para obtener todos los equipajes
    public List<Baggage> getAllBaggage() {
        return baggageRepository.findAll();
    }

    // Método para obtener equipaje por ID
    public Optional<Baggage> getBaggageById(Long id) {
        return baggageRepository.findById(id);
    }

    // Método para obtener equipajes por ID de reserva
    public List<Baggage> getBaggageByReservationId(Long reservationId) {
        List<Baggage> baggageList = baggageRepository.findByReservationId(reservationId);
        logger.info("Resultado de findByReservationId({}): {}", reservationId, baggageList);
        return baggageList != null ? baggageList : Collections.emptyList();
    }
}
