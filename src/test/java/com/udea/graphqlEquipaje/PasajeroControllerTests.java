package com.udea.graphqlEquipaje;

import com.udea.graphqlEquipaje.controller.PasajeroController;
import com.udea.graphqlEquipaje.entity.Pasajero;
import com.udea.graphqlEquipaje.repository.PasajeroRepository;
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

class PasajeroControllerTests {

    @Mock
    private PasajeroRepository pasajeroRepository;

    @InjectMocks
    private PasajeroController pasajeroController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodosLosPasajeros() {
        Pasajero pasajero1 = new Pasajero();
        Pasajero pasajero2 = new Pasajero();
        List<Pasajero> pasajeros = Arrays.asList(pasajero1, pasajero2);

        when(pasajeroRepository.findAll()).thenReturn(pasajeros);

        List<Pasajero> result = pasajeroController.todosLosPasajeros();
        assertEquals(2, result.size());
    }

    @Test
    void testPasajeroPorId() {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L);
        when(pasajeroRepository.findById(1L)).thenReturn(Optional.of(pasajero));

        Pasajero result = pasajeroController.pasajeroPorId(1L);
        assertEquals(pasajero, result);
    }

    @Test
    void testPasajeroPorIdNotFound() {
        when(pasajeroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pasajeroController.pasajeroPorId(1L));
    }

    @Test
    void testAgregarPasajero() {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre("Juan");
        pasajero.setApellido("Pérez");
        pasajero.setNumeroPasaporte("A12345678");
        pasajero.setNacionalidad("Colombiana");

        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(pasajero);

        Pasajero result = pasajeroController.agregarPasajero("Juan", "Pérez", "A12345678", "Colombiana");
        assertEquals(pasajero, result);
    }

    @Test
    void testEliminarPasajero() {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L);
        when(pasajeroRepository.findById(1L)).thenReturn(Optional.of(pasajero));

        Pasajero result = pasajeroController.eliminarPasajero(1L);
        verify(pasajeroRepository, times(1)).delete(pasajero);
        assertEquals(pasajero, result);
    }
}
