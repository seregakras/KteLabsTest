package com.example.ktelabstest.contollers;

import com.example.ktelabstest.dto.DoctorDTO;
import com.example.ktelabstest.model.Doctor;
import com.example.ktelabstest.services.DoctorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController extends GenericController<Doctor, DoctorDTO> {
    protected DoctorController(DoctorService doctorService) {
        super(doctorService);
    }
}
