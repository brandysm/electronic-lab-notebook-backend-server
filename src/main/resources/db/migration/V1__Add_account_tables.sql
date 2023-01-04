CREATE TABLE account (
    id      BIGSERIAL PRIMARY KEY,
    username    VARCHAR NOT NULL UNIQUE,
    email    VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

COMMENT ON TABLE account IS 'Table containing Electronic Lab Notebook Backend Server users'' account.';

CREATE TABLE account_role (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL
);

COMMENT ON TABLE account_role IS 'Table containing Electronic Lab Notebook Backend Server account roles.';

CREATE TABLE account_account_roles (
    accounts_id BIGINT NOT NULL REFERENCES account,
    account_roles_id BIGINT NOT NULL REFERENCES account_role,
    CONSTRAINT account_account_role_pkey PRIMARY KEY (accounts_id, account_roles_id)
);

COMMENT ON TABLE account_account_roles IS 'Table containing references between account and account_role tables.';
