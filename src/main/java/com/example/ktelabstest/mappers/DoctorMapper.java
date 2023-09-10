package com.example.ktelabstest.mappers;

import com.example.ktelabstest.dto.DoctorDTO;
import com.example.ktelabstest.model.Doctor;
import com.example.ktelabstest.model.GenericModel;
import com.example.ktelabstest.repositories.TalonRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DoctorMapper extends GenericMapper<Doctor, DoctorDTO> {
    private final TalonRepository talonRepository;

    public DoctorMapper(ModelMapper modelMapper, TalonRepository talonRepository) {
        super(Doctor.class, DoctorDTO.class, modelMapper);
        this.talonRepository = talonRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Doctor.class, DoctorDTO.class)
                .addMappings(m -> m.skip(DoctorDTO::setTalonIds))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(DoctorDTO.class, Doctor.class)
                .addMappings(m -> m.skip(Doctor::setTalons))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DoctorDTO source, Doctor destination) {
        if (!Objects.isNull(source.getTalonIds())) {
            destination.setTalons(talonRepository.findAllById(source.getTalonIds()));
        } else {
            destination.setTalons(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Doctor source, DoctorDTO destination) {
        destination.setTalonIds(getTalonIds(source));
    }

    private List<Long> getTalonIds(Doctor source) {
        return Objects.isNull(source) || Objects.isNull(source.getTalons()) ?
                Collections.emptyList() :
                source.getTalons()
                        .stream()
                        .map(GenericModel::getId)
                        .collect(Collectors.toList());
    }
}
