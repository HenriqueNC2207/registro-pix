CREATE TABLE pix
(
    id UUID NOT NULL,
    version    INTEGER                  NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    tipo_chave  VARCHAR(9) NOT NULL,
    valor_chave VARCHAR(77) NOT NULL,
    tipo_conta VARCHAR(10) NOT NULL,
    num_agencia VARCHAR(4) NOT NULL,
    num_conta VARCHAR(8) NOT NULL,
    nome_correntista VARCHAR(30) NOT NULL,
    sobrenome_correntista VARCHAR(45),
    tipo_pessoa CHAR(1) NOT NULL,
    PRIMARY KEY (id)
);