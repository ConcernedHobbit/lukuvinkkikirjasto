CREATE TABLE hints
(
    id     SERIAL PRIMARY KEY,
    header TEXT,
    type   TEXT
);

CREATE TABLE tags
(
    id   SERIAL PRIMARY KEY,
    hint INTEGER REFERENCES hints (id) ON DELETE CASCADE,
    tag  TEXT
);

CREATE TABLE video
(
    hint    INTEGER UNIQUE REFERENCES hints (id) ON DELETE CASCADE,
    url     TEXT,
    comment TEXT
);

CREATE TABLE book
(
    hint      INTEGER UNIQUE REFERENCES hints (id) ON DELETE CASCADE,
    author    TEXT,
    publisher TEXT,
    year      INT
);

CREATE table podcast
(
    hint        INTEGER UNIQUE REFERENCES hints (id) ON DELETE CASCADE,
    author      TEXT,
    name        TEXT,
    description TEXT
);

CREATE TABLE blog
(
    hint   INTEGER UNIQUE REFERENCES hints (id) ON DELETE CASCADE,
    author TEXT,
    url    TEXT
)
