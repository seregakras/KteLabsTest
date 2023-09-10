package com.example.ktelabstest.contollers;

import com.example.ktelabstest.dto.PatientDTO;
import com.example.ktelabstest.model.Patient;
import com.example.ktelabstest.services.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/patients")
public class PatientController extends GenericController<Patient, PatientDTO> {
    protected PatientController(PatientService patientService) {
        super(patientService);
    }
}
