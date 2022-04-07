CREATE TABLE hints
(
    id     SERIAL PRIMARY KEY,
    class  INT,
    year   INT,
    header TEXT,
    link   TEXT,
    author TEXT,
    publisher TEXT
);
