CREATE TABLE users
(
    id            NUMBER PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
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
    name                VARCHAR,
    platform            VARCHAR,
    sentry_key          VARCHAR,
    sentry_version      VARCHAR,
    sentry_client       VARCHAR,
    users_id            VARCHAR
);

CREATE SEQUENCE sq_project_id START WITH 1 INCREMENT BY 1;

CREATE TABLE problem
(
    id                  NUMBER PRIMARY KEY,
    project_id          NUMBER NOT NULL,
    event_id            VARCHAR,
    platform            VARCHAR,
    environment         VARCHAR,
    type                VARCHAR,
    timestamps          VARCHAR,
    levels              VARCHAR
);

CREATE SEQUENCE sq_problem_id START WITH 1 INCREMENT BY 1;

CREATE TABLE problem_item_mapping
(
    module_name         VARCHAR,
    module_value        VARCHAR,
    problem_id          NUMBER
);

CREATE TABLE contexts
(
    problem_id          NUMBER PRIMARY KEY
);

CREATE TABLE runtime
(
    contexts_id          NUMBER PRIMARY KEY,
    name                 VARCHAR,
    version              VARCHAR,
    build                VARCHAR
);

CREATE TABLE trace
(
    contexts_id          NUMBER PRIMARY KEY,
    trace_id             VARCHAR,
    span_id              VARCHAR,
    parent_span_id       VARCHAR,
    op                   VARCHAR,
    description          VARCHAR
);

CREATE TABLE exceptions
(
    id           NUMBER PRIMARY KEY
);

CREATE TABLE valuee
(
    id                   NUMBER PRIMARY KEY,
    type                 VARCHAR,
    valuee               VARCHAR,
    modules              VARCHAR,
    thread_id            NUMBER,
    exception_id         NUMBER
);

CREATE SEQUENCE sq_value_id START WITH 1 INCREMENT BY 1;

CREATE TABLE stacktrace
(
    value_id             NUMBER PRIMARY KEY
);

CREATE TABLE frame
(
    id                   NUMBER PRIMARY KEY,
    filename             VARCHAR,
    function             VARCHAR,
    modules              VARCHAR,
    lineno               NUMBER,
    native               BOOLEAN,
    in_app               BOOLEAN,
    stacktrace_id        NUMBER
);

CREATE SEQUENCE sq_frame_id START WITH 1 INCREMENT BY 1;

CREATE TABLE sdk
(
    problem_id           NUMBER PRIMARY KEY,
    name                 VARCHAR,
    version              VARCHAR
);

CREATE TABLE integrations
(
    sdk_id              NUMBER NOT NULL,
    integration         VARCHAR NOT NULL
);

CREATE TABLE package
(
    id                   NUMBER PRIMARY KEY,
    name                 VARCHAR,
    version              VARCHAR,
    sdk_id               NUMBER
);

CREATE SEQUENCE sq_package_id START WITH 1 INCREMENT BY 1;

CREATE TABLE request
(
    problem_id           NUMBER PRIMARY KEY,
    url                  VARCHAR,
    method               VARCHAR
);

CREATE TABLE request_item_mapping
(
    header_name          VARCHAR,
    header_value         VARCHAR,
    request_id           NUMBER
);

INSERT INTO role VALUES (next value for sq_role_id, 'ROLE_ADMIN');
INSERT INTO role VALUES (next value for sq_role_id, 'ROLE_USER');

--username: password
--user: user

INSERT INTO users VALUES (next value for sq_users_id, 'user', 'test@gmail.com', '$2a$04$EgxZlEheRk0jQGTIFE6PFe8hwN4a48Gisq5UpiL.98m09uQyKD0V.', true);

INSERT INTO users_role VALUES (1,1);