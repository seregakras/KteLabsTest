package com.example.ktelabstest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "talons")
public class Talon extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "doctor_id",
            foreignKey = @ForeignKey(name = "FK_TALON_DOCTOR")
    )
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id",
            foreignKey = @ForeignKey(name = "FK_TALON_PATIENT")
    )
    private Patient patient;

    @Column(name = "time_slot")
    private LocalTime timeSlot;

    @Column(name = "date_of_slot")
    private LocalDate dateOfSlot;

    @Column(name = "is_available")
    private boolean available = true;

    @Column(name = "is_actual")
    private boolean isActual = true;
}
