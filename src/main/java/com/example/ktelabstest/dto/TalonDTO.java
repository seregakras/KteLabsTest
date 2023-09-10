package com.example.ktelabstest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TalonDTO extends GenericDTO {
    private Long doctorId;
    private Long patientId;
    private LocalTime timeSlot;
    private LocalDate dateOfSlot;
    private boolean available = true;
    private boolean isActual = true;
}
