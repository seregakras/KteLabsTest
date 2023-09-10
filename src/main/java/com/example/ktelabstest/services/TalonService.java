package com.example.ktelabstest.services;

import com.example.ktelabstest.dto.TalonDTO;
import com.example.ktelabstest.mappers.TalonMapper;
import com.example.ktelabstest.model.GenericModel;
import com.example.ktelabstest.model.Talon;
import com.example.ktelabstest.repositories.DoctorRepository;
import com.example.ktelabstest.repositories.TalonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TalonService extends GenericService<Talon, TalonDTO> {
    private final TalonRepository talonRepository;
    private final DoctorRepository doctorRepository;
    private final TalonMapper talonMapper;

    protected TalonService(TalonRepository talonRepository, DoctorRepository doctorRepository,
                           TalonMapper talonMapper) {
        super(talonRepository, talonMapper);
        this.talonRepository = talonRepository;
        this.doctorRepository = doctorRepository;
        this.talonMapper = talonMapper;
    }

    public List<TalonDTO> findAvailableTalons(Long doctorId, LocalDate dateOfSlot) {
        return talonMapper.toDTOs(
                talonRepository.findTalonsByDoctor_IdAndDateOfSlotAndAvailableIsTrue(doctorId, dateOfSlot));
    }

    public TalonDTO orderTalon(Long patientId, Long talonId) {
        TalonDTO talonDTO = talonMapper.toDTO(talonRepository.findById(talonId).orElseThrow());
        talonDTO.setPatientId(patientId);
        talonDTO.setAvailable(false);
        return update(talonDTO);
    }

    public List<TalonDTO> findTalonsByPatientId(Long patientId) {
        return talonMapper.toDTOs(
                talonRepository.findTalonsByPatient_Id(patientId));
    }

    public List<TalonDTO> initTalons(LocalDate date, LocalTime startTime, int count, int duration) {
        List<TalonDTO> newTalons = new ArrayList<>();
        List<Long> doctorIds = doctorRepository.findAll().stream().map(GenericModel::getId).toList();
        for (Long doctorId : doctorIds) {
            LocalTime initTime = startTime;
            for (int i = 0; i < count; i++) {
                TalonDTO talonDTO = new TalonDTO();
                talonDTO.setDoctorId(doctorId);
                talonDTO.setTimeSlot(initTime);
                talonDTO.setDateOfSlot(date);
                TalonDTO newTalon = create(talonDTO);
                newTalons.add(newTalon);
                initTime = initTime.plusMinutes(duration);
            }
        }
        return newTalons;
    }
}
