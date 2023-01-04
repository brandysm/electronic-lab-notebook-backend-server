CREATE TABLE experiment (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL UNIQUE,
    creation_date TIMESTAMP NOT NULL
);

COMMENT ON TABLE experiment IS 'Table containing Electronic Lab Notebook Backend Server experiments.';

CREATE TABLE project_experiments (
    projects_id BIGINT NOT NULL REFERENCES project,
    experiments_id BIGINT NOT NULL REFERENCES experiment,
    CONSTRAINT project_experiment_pkey PRIMARY KEY (projects_id, experiments_id)
);

COMMENT ON TABLE project_experiments IS 'Table containing references between project and experiment tables.';