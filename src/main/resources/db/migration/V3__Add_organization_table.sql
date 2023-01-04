CREATE TABLE organization (
    id      BIGSERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL
);

COMMENT ON TABLE organization IS 'Table containing Electronic Lab Notebook Backend Server organizations.';

ALTER TABLE account ADD COLUMN organization_id BIGINT REFERENCES organization;