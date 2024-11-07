package com.udea.graphqlEquipaje;

import com.udea.graphqlEquipaje.controller.VueloController;
import com.udea.graphqlEquipaje.entity.Vuelo;
import com.udea.graphqlEquipaje.repository.VueloRepository;
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

class VueloControllerTests {

    @Mock
    private VueloRepository vueloRepository;

    @InjectMocks
    private VueloController vueloController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodosLosVuelos() {
        Vuelo vuelo1 = new Vuelo();
        Vuelo vuelo2 = new Vuelo();
        List<Vuelo> vuelos = Arrays.asList(vuelo1, vuelo2);

        when(vueloRepository.findAll()).thenReturn(vuelos);

        List<Vuelo> result = vueloController.todosLosVuelos();
        assertEquals(2, result.size());
    }

    @Test
    void testVueloPorId() {
        Vuelo vuelo = new Vuelo();
        vuelo.setId(1L);
        when(vueloRepository.findById(1L)).thenReturn(Optional.of(vuelo));

        Vuelo result = vueloController.vueloPorId(1L);
        assertEquals(vuelo, result);
    }

    @Test
    void testVueloPorIdNotFound() {
        when(vueloRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> vueloController.vueloPorId(1L));
    }

    @Test
    void testAgregarVuelo() {
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("AV123");
        vuelo.setOrigen("Medellín");
        vuelo.setDestino("Bogotá");
        vuelo.setFechaSalida("2024-11-15");

        when(vueloRepository.save(any(Vuelo.class))).thenReturn(vuelo);

        Vuelo result = vueloController.agregarVuelo("AV123", "Medellín", "Bogotá", "2024-11-15");
        assertEquals(vuelo, result);
    }

    @Test
    void testEliminarVuelo() {
        Vuelo vuelo = new Vuelo();
        vuelo.setId(1L);
        when(vueloRepository.findById(1L)).thenReturn(Optional.of(vuelo));

        Vuelo result = vueloController.eliminarVuelo(1L);
        verify(vueloRepository, times(1)).delete(vuelo);
        assertEquals(vuelo, result);
    }
}
