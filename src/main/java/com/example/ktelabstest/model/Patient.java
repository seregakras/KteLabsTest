package com.example.ktelabstest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient extends GenericModel {
    @Column(name = "patient_fio")
    private String patientFio;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "patient")
    private List<Talon> talons;
}
