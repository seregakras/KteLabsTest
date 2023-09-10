package com.example.ktelabstest.mappers;

import com.example.ktelabstest.dto.PatientDTO;
import com.example.ktelabstest.model.GenericModel;
import com.example.ktelabstest.model.Patient;
import com.example.ktelabstest.repositories.TalonRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PatientMapper extends GenericMapper<Patient, PatientDTO> {
    private final TalonRepository talonRepository;

    public PatientMapper(ModelMapper modelMapper, TalonRepository talonRepository) {
        super(Patient.class, PatientDTO.class, modelMapper);
        this.talonRepository = talonRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Patient.class, PatientDTO.class)
                .addMappings(m -> m.skip(PatientDTO::setTalonIds))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(PatientDTO.class, Patient.class)
                .addMappings(m -> m.skip(Patient::setTalons))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(PatientDTO source, Patient destination) {
        if (!Objects.isNull(source.getTalonIds())) {
            destination.setTalons(talonRepository.findAllById(source.getTalonIds()));
        } else {
            destination.setTalons(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Patient source, PatientDTO destination) {
        destination.setTalonIds(getTalonIds(source));
    }

    private List<Long> getTalonIds(Patient source) {
        return Objects.isNull(source) || Objects.isNull(source.getTalons()) ?
                Collections.emptyList() :
                source.getTalons()
                        .stream()
                        .map(GenericModel::getId)
                        .collect(Collectors.toList());
    }
}
