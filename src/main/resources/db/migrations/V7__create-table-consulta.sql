create table consultas
(
    id          serial primary key,
    medico_id   integer   not null,
    paciente_id integer   not null,
    data        timestamp     not null,

    primary key (id),
    constraint fk_medico foreign key (medico_id) references medicos (id),
    constraint fk_paciente foreign key (paciente_id) references pacientes (id)

)