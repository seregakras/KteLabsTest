package com.example.ktelabstest.model;

public enum Specialization {
    THERAPIST("терапевт"),
    SURGEON("хирург"),
    DENTIST("стоматолог"),
    CARDIOLOGIST("кардиолог"),
    RADIOLOGIST("рентгенолог");

    private final String description;

    Specialization(String description) {
        this.description = description;
    }
}
