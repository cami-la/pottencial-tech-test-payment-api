CREATE TABLE seller
(
    id           BIGINT NOT NULL,
    cpf          VARCHAR(255),
    name         VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    CONSTRAINT pk_seller PRIMARY KEY (id)
);

ALTER TABLE seller
    ADD CONSTRAINT uc_seller_cpf UNIQUE (cpf);

ALTER TABLE seller
    ADD CONSTRAINT uc_seller_email UNIQUE (email);

ALTER TABLE seller
    ADD CONSTRAINT uc_seller_name UNIQUE (name);

ALTER TABLE seller
    ADD CONSTRAINT uc_seller_phonenumber UNIQUE (phone_number);

INSERT INTO seller (id, cpf, name, email, phone_number) VALUES (1, '12345678901', 'Camila Cavalcante', 'camila@example.com', '123456789');