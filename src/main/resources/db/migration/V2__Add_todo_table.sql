CREATE TABLE todo (
    id      BIGSERIAL PRIMARY KEY,
    account_id      BIGINT NOT NULL REFERENCES account,
    title       VARCHAR NOT NULL,
    completed       BOOLEAN NOT NULL DEFAULT false
);

COMMENT ON TABLE todo IS 'Table containing Electronic Lab Notebook Backend Server users'' todos.';