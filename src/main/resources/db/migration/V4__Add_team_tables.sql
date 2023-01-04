CREATE TABLE team (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    organization_id BIGINT NOT NULL REFERENCES organization
);

COMMENT ON TABLE team IS 'Table containing Electronic Lab Notebook Backend Server teams.';

CREATE TABLE account_teams (
    accounts_id BIGINT NOT NULL REFERENCES account,
    teams_id BIGINT NOT NULL REFERENCES team,
    CONSTRAINT account_team_pkey PRIMARY KEY (accounts_id, teams_id)
);

COMMENT ON TABLE account_teams IS 'Table containing references between account and team tables.';