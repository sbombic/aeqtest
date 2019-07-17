CREATE TABLE TRANSFORMERS (
    id              IDENTITY NOT NULL PRIMARY KEY,
    name            VARCHAR(50) UNIQUE NOT NULL,
    type            CHAR(1) NOT NULL,
    strength        TINYINT NOT NULL,
    intelligence    TINYINT NOT NULL,
    speed           TINYINT NOT NULL,
    endurance       TINYINT NOT NULL,
    rank            TINYINT NOT NULL,
    courage         TINYINT NOT NULL,
    firepower       TINYINT NOT NULL,
    skill           TINYINT NOT NULL
);

CREATE INDEX idx_aff_type ON TRANSFORMERS(type);