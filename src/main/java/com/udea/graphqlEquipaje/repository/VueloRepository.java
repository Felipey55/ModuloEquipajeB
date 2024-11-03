package com.udea.graphqlEquipaje.repository;

import com.udea.graphqlEquipaje.entity.Pasajero;
import com.udea.graphqlEquipaje.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {

}
