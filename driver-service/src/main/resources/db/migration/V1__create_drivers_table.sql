CREATE TABLE drivers
(
    id UUID PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    phone_number VARCHAR(20) NOT NULL UNIQUE,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL
);