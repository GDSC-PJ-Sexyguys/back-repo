package com.example.GDSC_hackathon.domain;

import ch.qos.logback.core.model.INamedModel;
import com.example.GDSC_hackathon.enumClass.ClassroomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String building;

    @Column(nullable = false)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassroomType type;

    @Column
    private Integer totalSeat;


}