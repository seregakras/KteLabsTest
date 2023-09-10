package com.example.ktelabstest.services;

import com.example.ktelabstest.dto.PatientDTO;
import com.example.ktelabstest.mappers.PatientMapper;
import com.example.ktelabstest.model.Patient;
import com.example.ktelabstest.repositories.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends GenericService<Patient, PatientDTO> {
    protected PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        super(patientRepository, patientMapper);
    }
}
