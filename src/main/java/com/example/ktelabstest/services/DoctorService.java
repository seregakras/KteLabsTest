package com.example.ktelabstest.services;

import com.example.ktelabstest.dto.DoctorDTO;
import com.example.ktelabstest.mappers.DoctorMapper;
import com.example.ktelabstest.model.Doctor;
import com.example.ktelabstest.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends GenericService<Doctor, DoctorDTO> {

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        super(doctorRepository, doctorMapper);
    }
}
