package com.example.ktelabstest.repositories;

import com.example.ktelabstest.model.Talon;

import java.time.LocalDate;
import java.util.List;

public interface TalonRepository extends GenericRepository<Talon> {
    List<Talon> findTalonsByDoctor_IdAndDateOfSlotAndAvailableIsTrue(Long doctorId, LocalDate dateOfSlot);

    List<Talon> findTalonsByPatient_Id(Long patientId);
}
