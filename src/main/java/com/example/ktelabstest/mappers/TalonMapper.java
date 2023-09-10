package com.example.ktelabstest.mappers;

import com.example.ktelabstest.dto.TalonDTO;
import com.example.ktelabstest.model.Talon;
import com.example.ktelabstest.repositories.DoctorRepository;
import com.example.ktelabstest.repositories.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TalonMapper extends GenericMapper<Talon, TalonDTO> {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public TalonMapper(ModelMapper modelMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        super(Talon.class, TalonDTO.class, modelMapper);
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Talon.class, TalonDTO.class)
                .addMappings(m -> m.skip(TalonDTO::setDoctorId))
                .addMappings(m -> m.skip(TalonDTO::setPatientId))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(TalonDTO.class, Talon.class)
                .addMappings(m -> m.skip(Talon::setDoctor))
                .addMappings(m -> m.skip(Talon::setPatient))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(TalonDTO source, Talon destination) {
        if (source.getDoctorId() != null) {
            destination.setDoctor(doctorRepository.findById(source.getDoctorId()).orElseThrow());
        } else {
            destination.setDoctor(null);
        }
        if (source.getPatientId() != null) {
            destination.setPatient(patientRepository.findById(source.getPatientId()).orElseThrow());
        } else {
            destination.setPatient(null);
        }
    }

    @Override
    protected void mapSpecificFields(Talon source, TalonDTO destination) {
        if (source.getDoctor() != null) {
            destination.setDoctorId(source.getDoctor().getId());
        } else {
            destination.setDoctorId(null);
        }
        if (source.getPatient() != null) {
            destination.setPatientId(source.getPatient().getId());
        } else {
            destination.setPatientId(null);
        }
    }
}
