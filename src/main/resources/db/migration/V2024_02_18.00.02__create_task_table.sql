SET search_path TO db,ext,public;

CREATE TABLE db.task (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    done BOOLEAN NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT current_timestamp,
    priority TEXT NOT NULL
)