CREATE TABLE cliente (
    cod_cliente BIGSERIAL PRIMARY KEY,
    nme_cliente VARCHAR(100) NOT NULL,
    dta_nascimento DATE,
    nro_cpf VARCHAR(11) NOT NULL,
    dta_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)