CREATE TABLE consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,

	medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    data TIMESTAMP NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_consulta_medico_id FOREIGN KEY (medico_id)
        REFERENCES medico (id),

    CONSTRAINT fk_consulta_paciente_id FOREIGN KEY (paciente_id)
        REFERENCES paciente (id)
);