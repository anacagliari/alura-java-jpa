CREATE TABLE series (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR UNIQUE,
    total_temporadas INTEGER,
    avaliacao DOUBLE PRECISION,
    genero VARCHAR,
    atores TEXT,
    poster TEXT,
    sinopse TEXT
);
