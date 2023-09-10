create table doctors
(
    id             bigserial
        primary key,
    when_created   timestamp(6),
    doctor_fio     varchar(255),
    specialization varchar(255)
        constraint doctors_specialization_check
            check ((specialization)::text = ANY
        ((ARRAY ['THERAPIST'::character varying, 'SURGEON'::character varying, 'DENTIST'::character varying, 'CARDIOLOGIST'::character varying, 'RADIOLOGIST'::character varying])::text[])),
    uuid           varchar(255)
);

alter table doctors
    owner to "user";

create table patients
(
    birthdate    date,
    id           bigserial
        primary key,
    when_created timestamp(6),
    address      varchar(255),
    patient_fio  varchar(255),
    uuid         varchar(255)
);

alter table patients
    owner to "user";

create table talons
(
    date_of_slot date,
    is_actual    boolean,
    is_available boolean,
    time_slot    time(6),
    doctor_id    bigint
        constraint fk_talon_doctor
            references doctors,
    id           bigserial
        primary key,
    patient_id   bigint
        constraint fk_talon_patient
            references patients,
    when_created timestamp(6),
    uuid         varchar(255)
);

alter table talons
    owner to "user";

