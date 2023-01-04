CREATE TABLE project (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    organization_id BIGINT REFERENCES organization,
    team_id BIGINT REFERENCES team
);

COMMENT ON TABLE project IS 'Table containing Electronic Lab Notebook Backend Server projects.';

CREATE TABLE account_projects (
    accounts_id BIGINT NOT NULL REFERENCES account,
    projects_id BIGINT NOT NULL REFERENCES project,
    CONSTRAINT account_project_pkey PRIMARY KEY (accounts_id, projects_id)
);

COMMENT ON TABLE account_projects IS 'Table containing references between account and project tables.';