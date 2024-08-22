CREATE TYPE status_ocorrencia AS ENUM ('ATIVA', 'FINALIZADA');

CREATE TABLE endereco (
                          cod_endereco BIGSERIAL PRIMARY KEY,
                          nme_logradouro VARCHAR(255) NOT NULL,
                          nme_bairro VARCHAR(100) NOT NULL,
                          nro_cep VARCHAR(8) NOT NULL,
                          nme_cidade VARCHAR(100) NOT NULL,
                          nme_estado VARCHAR(2) NOT NULL
);

CREATE TABLE ocorrencia (
    cod_ocorrencia BIGSERIAL PRIMARY KEY,
    cod_cliente BIGINT NOT NULL,
    cod_endereco BIGINT NOT NULL,
    dta_ocorrencia TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sta_ocorrencia status_ocorrencia NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cod_cliente) REFERENCES cliente(cod_cliente) ON DELETE CASCADE,
    CONSTRAINT fk_endereco FOREIGN KEY (cod_endereco) REFERENCES endereco(cod_endereco)
);

CREATE TABLE foto_ocorrencia (
    cod_foto_ocorrencia BIGSERIAL PRIMARY KEY,
    cod_ocorrencia BIGINT NOT NULL,
    dta_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dsc_path_bucket VARCHAR(100) NOT NULL,
    dsc_hash VARCHAR(255) NOT NULL,
    CONSTRAINT fk_ocorrencia FOREIGN KEY (cod_ocorrencia) REFERENCES ocorrencia(cod_ocorrencia) ON DELETE CASCADE
)