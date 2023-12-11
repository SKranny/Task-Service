CREATE TABLE IF NOT EXISTS task (
    id                          BIGSERIAL PRIMARY KEY,
    author_id                   BIGINT NOT NULL,
    executor_id                 BIGINT NOT NULL,
    title                       VARCHAR(255) NOT NULL,
    description                 TEXT NOT NULL,
    status                      VARCHAR(50) NOT NULL,
    priority               VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS comment (
    id                          BIGSERIAL PRIMARY KEY,
    author_id                   BIGINT NOT NULL,
    task_id                     BIGINT NOT NULL,
    text                        TEXT NOT NULL
);