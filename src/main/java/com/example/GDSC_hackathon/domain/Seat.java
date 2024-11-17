package com.example.GDSC_hackathon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private boolean hasSocket;

    @Column(nullable = false)
    private boolean isReserved;
}
