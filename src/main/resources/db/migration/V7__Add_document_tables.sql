CREATE TABLE document (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL UNIQUE,
    creation_date TIMESTAMP NOT NULL,
    data VARCHAR NOT NULL
);

COMMENT ON TABLE document IS 'Table containing Electronic Lab Notebook Backend Server documents.';

CREATE TABLE experiment_documents (
    experiments_id BIGINT NOT NULL REFERENCES experiment,
    documents_id BIGINT NOT NULL REFERENCES document,
    CONSTRAINT experiment_document_pkey PRIMARY KEY (experiments_id, documents_id)
);

COMMENT ON TABLE experiment_documents IS 'Table containing references between experiment and document tables.';