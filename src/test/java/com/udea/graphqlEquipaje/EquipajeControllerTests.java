package com.udea.graphqlEquipaje;

import com.udea.graphqlEquipaje.controller.EquipajeController;
import com.udea.graphqlEquipaje.entity.Equipaje;
import com.udea.graphqlEquipaje.repository.EquipajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EquipajeControllerTests {

    @Mock
    private EquipajeRepository equipajeRepository;

    @InjectMocks
    private EquipajeController equipajeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodosLosEquipajes() {
        Equipaje equipaje1 = new Equipaje();
        Equipaje equipaje2 = new Equipaje();
        List<Equipaje> equipajes = Arrays.asList(equipaje1, equipaje2);

        when(equipajeRepository.findAll()).thenReturn(equipajes);

        List<Equipaje> result = equipajeController.todosLosEquipajes();
        assertEquals(2, result.size());
    }

    @Test
    void testEquipajePorId() {
        Equipaje equipaje = new Equipaje();
        equipaje.setId(1L);
        when(equipajeRepository.findById(1L)).thenReturn(Optional.of(equipaje));

        Equipaje result = equipajeController.equipajePorId(1L);
        assertEquals(equipaje, result);
    }

    @Test
    void testEquipajePorIdNotFound() {
        when(equipajeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> equipajeController.equipajePorId(1L));
    }

    @Test
    void testAgregarEquipaje() {
        Equipaje equipaje = new Equipaje();
        equipaje.setAlto(10.0f);
        equipaje.setLargo(20.0f);
        equipaje.setAncho(15.0f);
        equipaje.setPeso(5.0f);
        equipaje.setTipo("Maleta");
        equipaje.setUbicacion("A1");
        equipaje.setValor(100.0f);

        when(equipajeRepository.save(any(Equipaje.class))).thenReturn(equipaje);

        Equipaje result = equipajeController.agregarEquipaje(10.0f, 20.0f, 15.0f, 5.0f, "Maleta", "A1", 100.0f);
        assertEquals(equipaje, result);
    }

    @Test
    void testEliminarEquipaje() {
        Equipaje equipaje = new Equipaje();
        equipaje.setId(1L);
        when(equipajeRepository.findById(1L)).thenReturn(Optional.of(equipaje));

        Equipaje result = equipajeController.eliminarEquipaje(1L);
        verify(equipajeRepository, times(1)).deleteById(1L);
        assertEquals(equipaje, result);
    }

    @Test
    void testActualizarEquipaje() {
        Equipaje equipajeExistente = new Equipaje();
        equipajeExistente.setId(1L);
        equipajeExistente.setAlto(50.0f);

        when(equipajeRepository.findById(1L)).thenReturn(Optional.of(equipajeExistente));

        Equipaje equipajeActualizado = new Equipaje();
        equipajeActualizado.setId(1L);
        equipajeActualizado.setAlto(60.0f);
        equipajeActualizado.setLargo(40.0f);
        equipajeActualizado.setAncho(30.0f);
        equipajeActualizado.setPeso(20.0f);
        equipajeActualizado.setTipo("Maleta");
        equipajeActualizado.setUbicacion("Terminal A");
        equipajeActualizado.setValor(200.0f);

        when(equipajeRepository.save(any(Equipaje.class))).thenReturn(equipajeActualizado);

        Equipaje resultado = equipajeController.actualizarEquipaje(1L, 60.0f, 40.0f, 30.0f, 20.0f, "Maleta", "Terminal A", 200.0f);

        assertEquals(60.0f, resultado.getAlto());
        assertEquals(40.0f, resultado.getLargo());
        assertEquals("Maleta", resultado.getTipo());
    }
}
