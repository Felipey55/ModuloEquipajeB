package com.udea.graphqlEquipaje.repository;

import com.udea.graphqlEquipaje.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
    List<Baggage> findByReservationId(Long reservationId);
}
