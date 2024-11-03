package com.udea.graphqlEquipaje.repository;

import com.udea.graphqlEquipaje.entity.Equipaje;
import com.udea.graphqlEquipaje.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

}
