package com.udea.graphqlEquipaje.service;

import com.udea.graphqlEquipaje.entity.Baggage;
import com.udea.graphqlEquipaje.entity.Payment;
import com.udea.graphqlEquipaje.repository.BaggageRepository;
import com.udea.graphqlEquipaje.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BaggageRepository baggageRepository;

    public PaymentService(PaymentRepository paymentRepository, BaggageRepository baggageRepository) {
        this.paymentRepository = paymentRepository;
        this.baggageRepository = baggageRepository;
    }

    public Payment recordPayment(Long baggageId, Float additionalCharge, Float totalAmount, LocalDate paymentDate) {
        // Buscar el equipaje asociado al pago
        Baggage baggage = baggageRepository.findById(baggageId)
                .orElseThrow(() -> new RuntimeException("Baggage not found"));

        // Crear una nueva instancia de Payment y asignar los valores
        Payment payment = new Payment();
        payment.setBaggage(baggage);
        payment.setAdditionalCharge(additionalCharge);
        payment.setTotalAmount(totalAmount);
        payment.setPaymentDate(paymentDate);

        // Guardar el pago en la base de datos
        return paymentRepository.save(payment);
    }
}
