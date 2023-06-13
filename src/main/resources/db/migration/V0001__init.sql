CREATE TABLE users
(
    id            NUMBER PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    active        BOOLEAN NOT NULL
);

CREATE SEQUENCE sq_users_id START WITH 1 INCREMENT BY 1;

CREATE TABLE role
(
    id             NUMBER PRIMARY KEY,
    name           VARCHAR(255) NOT NULL
);

CREATE SEQUENCE sq_role_id START WITH 1 INCREMENT BY 1;

CREATE TABLE users_role
(
    users_id       NUMBER NOT NULL,
    role_id        NUMBER NOT NULL
);

CREATE TABLE project
(
    id                  NUMBER PRIMARY KEY,
    sentry_key          VARCHAR,
    sentry_version      VARCHAR,
    sentry_client       VARCHAR
);

CREATE TABLE problem
(
    id                  NUMBER PRIMARY KEY,
    project_id          NUMBER NOT NULL,
    event               VARCHAR,
    type                VARCHAR,
    detail              VARCHAR
);

CREATE SEQUENCE sq_problem_id START WITH 1 INCREMENT BY 1;

INSERT INTO role VALUES (next value for sq_role_id, 'ROLE_ADMIN');
INSERT INTO role VALUES (next value for sq_role_id, 'ROLE_USER');

--username: password
--user: user

INSERT INTO users VALUES (next value for sq_users_id, 'user', '$2a$04$EgxZlEheRk0jQGTIFE6PFe8hwN4a48Gisq5UpiL.98m09uQyKD0V.', true);

INSERT INTO users_role VALUES (1,1);