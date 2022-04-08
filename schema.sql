CREATE TABLE hints
(
    id        SERIAL PRIMARY KEY,
    class     INT,
    year      INT,
    header    TEXT,
    link      TEXT,
    author    TEXT,
    publisher TEXT
);

CREATE TABLE tags
(
    id   SERIAL PRIMARY KEY,
    hint INTEGER REFERENCES hints (id) ON DELETE CASCADE,
    tag  TEXT
)
