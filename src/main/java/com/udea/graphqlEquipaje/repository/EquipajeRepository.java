package com.udea.graphqlEquipaje.repository;

import com.udea.graphqlEquipaje.entity.Equipaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipajeRepository extends JpaRepository<Equipaje, Long> {
    List<Equipaje> findByVueloId(Long vueloId);
    List<Equipaje> findByPasajeroId(Long pasajeroId);
}
