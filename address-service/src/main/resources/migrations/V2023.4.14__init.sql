CREATE TABLE address (
                         id IDENTITY PRIMARY KEY,
                         direction VARCHAR(512) NOT NULL,
                         enabled BOOLEAN NOT NULL DEFAULT TRUE,
                         version BIGINT NOT NULL DEFAULT 0,
                         created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP
);
