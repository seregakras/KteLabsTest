package com.example.ktelabstest.dto;

import com.example.ktelabstest.model.Specialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DoctorDTO extends GenericDTO {
    private String doctorFio;
    private Specialization specialization;
    private List<Long> talonIds;

}
