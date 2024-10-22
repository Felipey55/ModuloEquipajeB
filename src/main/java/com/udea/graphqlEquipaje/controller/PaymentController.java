package com.udea.graphqlEquipaje.controller;

import com.udea.graphqlEquipaje.entity.Payment;
import com.udea.graphqlEquipaje.service.PaymentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @MutationMapping
    public Payment recordPayment(@Argument Long baggageId, @Argument Float additionalCharge,
                                 @Argument Float totalAmount, @Argument String paymentDate) {
        LocalDate payDate = LocalDate.parse(paymentDate);
        return paymentService.recordPayment(baggageId, additionalCharge, totalAmount, payDate);
    }
}
