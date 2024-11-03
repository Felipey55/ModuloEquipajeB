package com.udea.graphqlEquipaje.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private String baggageType;
    private String baggageLocation;
    private String description;
    private Float basePrice;
    private Float additionalCharge;
    private Float dimensions;
    private Float weight;
    private int quantity;
    private Float totalCost;

    @OneToOne(mappedBy = "baggage", cascade = CascadeType.ALL)
    private Payment payment;
}