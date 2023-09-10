package com.example.ktelabstest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientDTO extends GenericDTO {
    private String patientFio;
    private Date birthDate;
    private String address;
    private List<Long> talonIds;
}
