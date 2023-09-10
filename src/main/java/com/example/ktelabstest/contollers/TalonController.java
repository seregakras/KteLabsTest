package com.example.ktelabstest.contollers;

import com.example.ktelabstest.dto.TalonDTO;
import com.example.ktelabstest.model.Talon;
import com.example.ktelabstest.services.TalonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping(value = "/talons")
public class TalonController extends GenericController<Talon, TalonDTO> {
    protected TalonController(TalonService talonService) {
        super(talonService);
    }

    @GetMapping("/available")
    public ResponseEntity<List<TalonDTO>> findAvailableTalons(
            @RequestParam(name = "doctorId") Long doctorId,
            @RequestParam(name = "dateOfSlot") LocalDate dateOfSlot) {
        return ResponseEntity.status(HttpStatus.OK).body(((TalonService) genericService)
                .findAvailableTalons(doctorId, dateOfSlot));
    }

    @GetMapping("/order")
    public ResponseEntity<TalonDTO> findAvailableTalons(
            @RequestParam(name = "patientId") Long patientId,
            @RequestParam(name = "talonId") Long talonId) {
        return ResponseEntity.status(HttpStatus.OK).body(((TalonService) genericService)
                .orderTalon(patientId, talonId));
    }

    @GetMapping("/busy")
    public ResponseEntity<List<TalonDTO>> findATalonsByPatient(
            @RequestParam(name = "patientId") Long patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(((TalonService) genericService)
                .findTalonsByPatientId(patientId));
    }

    @GetMapping("/init")
    public ResponseEntity<List<TalonDTO>> initTalons(
            @RequestParam(name = "date") LocalDate date,
            @RequestParam(name = "startTime")LocalTime startTime,
            @RequestParam(name = "count") int count,
            @RequestParam(name = "duration") int duration) {
        return ResponseEntity.status(HttpStatus.OK).body(((TalonService) genericService)
                .initTalons(date, startTime, count, duration));
    }
}
