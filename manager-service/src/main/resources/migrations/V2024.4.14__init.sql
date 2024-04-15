CREATE TABLE center_authorization (
                         id IDENTITY PRIMARY KEY,
                         authorization_number VARCHAR(256) NOT NULL UNIQUE,
                         version BIGINT NOT NULL DEFAULT 0,
                         created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP
);

CREATE TABLE manager (
                         id IDENTITY PRIMARY KEY,
                         name VARCHAR(128) NOT NULL,
                         nif VARCHAR(16) NOT NULL,
                         address_id BIGINT NOT NULL,
                         enabled BOOLEAN NOT NULL DEFAULT TRUE,
                         version BIGINT NOT NULL DEFAULT 0,
                         created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP
);

CREATE TABLE manager_authorization (
                                       manager_id BIGINT NOT NULL,
                                       center_authorization_id BIGINT NOT NULL,
                                       created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       CONSTRAINT pk_manager_authorization PRIMARY KEY (manager_id, center_authorization_id),
                                       CONSTRAINT fk_manager_authorization_manager FOREIGN KEY (manager_id) REFERENCES manager(id),
                                       CONSTRAINT fk_manager_authorization_authorization FOREIGN KEY (center_authorization_id) REFERENCES center_authorization(id)
);
