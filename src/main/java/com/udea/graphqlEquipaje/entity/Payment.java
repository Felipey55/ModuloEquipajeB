package com.udea.graphqlEquipaje.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "baggage_id")
    private Baggage baggage;

    private Float additionalCharge;
    private Float totalAmount;
    private LocalDate paymentDate;
}
