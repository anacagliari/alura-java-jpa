CREATE TABLE episodios (
    id BIGSERIAL PRIMARY KEY,
    temporada INTEGER,
    titulo VARCHAR,
    numero_episodio INTEGER,
    avaliacao DOUBLE PRECISION,
    data_lancamento DATE,
    serie_id BIGINT,
        CONSTRAINT fk_serie
        FOREIGN KEY (serie_id)
        REFERENCES series (id)
        ON DELETE CASCADE
);